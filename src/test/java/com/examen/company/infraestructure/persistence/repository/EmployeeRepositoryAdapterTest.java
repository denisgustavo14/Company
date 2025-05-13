package com.examen.company.infraestructure.persistence.repository;

import com.examen.company.domain.model.Employee;
import com.examen.company.domain.valueobject.Gender;
import com.examen.company.infraestructure.exception.CompanyException;
import com.examen.company.infraestructure.persistence.mapper.EmployeeMapper;
import com.examen.company.infraestructure.web.dto.EmployeeRequest;
import com.examen.company.infraestructure.web.dto.EmployeeResponse;
import com.examen.company.shared.enums.ErrorCodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeRepositoryAdapterTest {

    @Mock
    private EmployeeRepositoryJPA employeeRepositoryJPA;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeRepositoryAdapter repositoryAdapter;

    private Employee mockEmployee;
    private EmployeeResponse mockResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockEmployee = new Employee();
        mockEmployee.setId(1L);
        mockEmployee.setFirstName("Juan");
        mockEmployee.setMidName("Carlos");
        mockEmployee.setFatherLastName("Perez");
        mockEmployee.setMotherLastName("Lopez");
        mockEmployee.setAge(30);
        mockEmployee.setGender(Gender.MALE);
        mockEmployee.setBirthdate(LocalDate.of(1994, 5, 13));
        mockEmployee.setPosition("Developer");

        mockResponse = new EmployeeResponse();
        mockResponse.setId(1L);
        mockResponse.setFirstName("Juan");
        mockResponse.setMidName("Carlos");
        mockResponse.setFatherLastName("Perez");
        mockResponse.setMotherLastName("Lopez");
        mockResponse.setAge(30);
        mockResponse.setGender(Gender.MALE);
        mockResponse.setBirthdate(LocalDate.of(1994, 5, 13));
        mockResponse.setPosition("Developer");
    }

    @Test
    void testGetEmployeeId_success() {
        when(employeeRepositoryJPA.findEmployeeById(1L)).thenReturn(Optional.of(mockEmployee));
        when(employeeMapper.toEmployeeResponse(mockEmployee)).thenReturn(mockResponse);

        EmployeeResponse result = repositoryAdapter.getEmployeeId(1L);

        assertNotNull(result);
        assertEquals("Juan", result.getFirstName());
    }

    @Test
    void testGetEmployeeId_notFound() {
        when(employeeRepositoryJPA.findEmployeeById(999L)).thenReturn(Optional.empty());

        CompanyException ex = assertThrows(CompanyException.class, () -> {
            repositoryAdapter.getEmployeeId(999L);
        });

        assertEquals(ErrorCodes.NOT_FOUND.getCode(), ex.getErrorCode());
    }

    @Test
    void testGetAllEmployees_success() {
        when(employeeRepositoryJPA.findAll()).thenReturn(List.of(mockEmployee));
        when(employeeMapper.toEmployeeResponseList(List.of(mockEmployee))).thenReturn(List.of(mockResponse));

        List<EmployeeResponse> result = repositoryAdapter.getAllEmployees();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testDeleteEmployeeById_success() {
        when(employeeRepositoryJPA.findEmployeeById(1L)).thenReturn(Optional.of(mockEmployee));
        when(employeeMapper.toEmployeeResponse(mockEmployee)).thenReturn(mockResponse);
        doNothing().when(employeeRepositoryJPA).deleteById(1L);

        assertDoesNotThrow(() -> repositoryAdapter.deleteEmployeeById(1L));

        verify(employeeRepositoryJPA).deleteById(1L);
    }

    @Test
    void testDeleteEmployeeById_failure() {
        when(employeeRepositoryJPA.findEmployeeById(2L)).thenReturn(Optional.empty());

        assertThrows(CompanyException.class, () -> repositoryAdapter.deleteEmployeeById(2L));
    }

    @Test
    void testPartialUpdate_success() {
        Map<String, Object> updates = Map.of("firstName", "Pedro");

        when(employeeRepositoryJPA.findEmployeeById(1L)).thenReturn(Optional.of(mockEmployee));
        when(employeeRepositoryJPA.save(any(Employee.class))).thenReturn(mockEmployee);
        when(employeeMapper.toEmployeeResponse(any(Employee.class))).thenReturn(mockResponse);

        EmployeeResponse result = repositoryAdapter.partialUpdate(1L, updates);

        assertNotNull(result);
        assertEquals("Juan", result.getFirstName());
    }

    @Test
    void testPartialUpdate_invalidKey() {
        Map<String, Object> updates = Map.of("invalidKey", "value");

        when(employeeRepositoryJPA.findEmployeeById(1L)).thenReturn(Optional.of(mockEmployee));

        CompanyException ex = assertThrows(CompanyException.class, () ->
                repositoryAdapter.partialUpdate(1L, updates)
        );

        assertEquals(ErrorCodes.BAD_REQUEST.getCode(), ex.getErrorCode());
    }

    @Test
    void testCreateEmployee_success() {
        EmployeeRequest request = new EmployeeRequest(
                "Juan", "Carlos", "Perez", "Lopez",
                30, Gender.MALE, LocalDate.of(1994, 5, 13), "Developer"
        );

        when(employeeMapper.toEmployee(request)).thenReturn(mockEmployee);
        when(employeeRepositoryJPA.save(mockEmployee)).thenReturn(mockEmployee);
        when(employeeRepositoryJPA.findEmployeeById(mockEmployee.getId())).thenReturn(Optional.of(mockEmployee));
        when(employeeMapper.toEmployeeResponse(mockEmployee)).thenReturn(mockResponse);

        EmployeeResponse result = repositoryAdapter.createEmployee(request);

        assertNotNull(result);
        assertEquals("Juan", result.getFirstName());
    }
}
