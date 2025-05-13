package com.examen.company.infraestructure.web.controller;

import com.examen.company.application.port.in.EmployeePort;
import com.examen.company.domain.valueobject.Gender;
import com.examen.company.infraestructure.web.dto.DeleteResponse;
import com.examen.company.infraestructure.web.dto.EmployeeRequest;
import com.examen.company.infraestructure.web.dto.EmployeeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeePort employeePort;

    private EmployeeResponse employeeResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employeeResponse = new EmployeeResponse();
        employeeResponse.setId(1L);
        employeeResponse.setFirstName("Juan");
        employeeResponse.setMidName("Carlos");
        employeeResponse.setFatherLastName("Gomez");
        employeeResponse.setMotherLastName("Lopez");
        employeeResponse.setAge(30);
        employeeResponse.setGender(Gender.MALE);
        employeeResponse.setBirthdate(LocalDate.of(1995, 5, 13));
        employeeResponse.setPosition("Developer");
    }

    @Test
    void testGetEmployeeById() {
        when(employeePort.getEmployeeById(1L)).thenReturn(employeeResponse);

        EmployeeResponse response = employeeController.getEmployeeById(1L);

        assertNotNull(response);
        assertEquals("Juan", response.getFirstName());
        verify(employeePort).getEmployeeById(1L);
    }

    @Test
    void testGetAllEmployees() {
        when(employeePort.getAllEmployees()).thenReturn(List.of(employeeResponse));

        List<EmployeeResponse> result = employeeController.getAllEmployees();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getFirstName());
        verify(employeePort).getAllEmployees();
    }

    @Test
    void testDeleteEmployeeById() {
        doNothing().when(employeePort).deleteEmployeeById(1L);

        DeleteResponse response = employeeController.deleteEmployeeById(1L);

        assertNotNull(response);
        assertTrue(response.getMessage().contains("1"));
        verify(employeePort).deleteEmployeeById(1L);
    }

    @Test
    void testUpdateEmployeeById() {
        Map<String, Object> updates = Map.of("position", "Tech Lead");

        when(employeePort.partialUpdateEmp(1L, updates)).thenReturn(employeeResponse);

        EmployeeResponse updated = employeeController.updateEmployeeById(1L, updates);

        assertNotNull(updated);
        verify(employeePort).partialUpdateEmp(1L, updates);
    }

    @Test
    void testCreateEmployee() {
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Juan");
        request.setMidName("Carlos");
        request.setFatherLastName("Gomez");
        request.setMotherLastName("Lopez");
        request.setAge(30);
        request.setGender(Gender.MALE);
        request.setBirthdate(LocalDate.of(1995, 5, 13));
        request.setPosition("Developer");

        when(employeePort.createEmployee(request)).thenReturn(employeeResponse);

        EmployeeResponse result = employeeController.createEmployee(request);

        assertNotNull(result);
        assertEquals("Juan", result.getFirstName());
        verify(employeePort).createEmployee(request);
    }

}
