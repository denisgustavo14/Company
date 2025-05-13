package com.examen.company.application.usecase;

import com.examen.company.application.port.out.EmployeeRepository;
import com.examen.company.domain.valueobject.Gender;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private EmployeeResponse mockResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockResponse = new EmployeeResponse();
        mockResponse.setId(1L);
        mockResponse.setFirstName("Juan");
        mockResponse.setMidName("Carlos");
        mockResponse.setFatherLastName("Gomez");
        mockResponse.setMotherLastName("Lopez");
        mockResponse.setAge(30);
        mockResponse.setGender(Gender.MALE);
        mockResponse.setBirthdate(LocalDate.of(1994, 5, 13));
        mockResponse.setPosition("Developer");
    }

    @Test
    void testGetEmployeeById() {
        when(employeeRepository.getEmployeeId(1L)).thenReturn(mockResponse);

        EmployeeResponse result = employeeService.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals("Juan", result.getFirstName());
        verify(employeeRepository).getEmployeeId(1L);
    }

    @Test
    void testGetAllEmployees() {
        when(employeeRepository.getAllEmployees()).thenReturn(List.of(mockResponse));

        List<EmployeeResponse> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeeRepository).getAllEmployees();
    }

    @Test
    void testDeleteEmployeeById() {
        doNothing().when(employeeRepository).deleteEmployeeById(1L);

        employeeService.deleteEmployeeById(1L);

        verify(employeeRepository).deleteEmployeeById(1L);
    }

    @Test
    void testPartialUpdateEmp() {
        Map<String, Object> updates = Map.of("position", "Tech Lead");
        when(employeeRepository.partialUpdate(1L, updates)).thenReturn(mockResponse);

        EmployeeResponse result = employeeService.partialUpdateEmp(1L, updates);

        assertNotNull(result);
        assertEquals("Juan", result.getFirstName());
        verify(employeeRepository).partialUpdate(1L, updates);
    }

    @Test
    void testCreateEmployee() {
        EmployeeRequest request = new EmployeeRequest(
                "Juan",
                "Carlos",
                "Gomez",
                "Lopez",
                30,
                Gender.MALE,
                LocalDate.of(1994, 5, 13),
                "Developer"
        );

        when(employeeRepository.createEmployee(request)).thenReturn(mockResponse);

        EmployeeResponse result = employeeService.createEmployee(request);

        assertNotNull(result);
        assertEquals("Juan", result.getFirstName());
        verify(employeeRepository).createEmployee(request);
    }
}
