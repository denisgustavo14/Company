package com.examen.company.application.port.out;

import com.examen.company.infraestructure.web.dto.EmployeeRequest;
import com.examen.company.infraestructure.web.dto.EmployeeResponse;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository {

    EmployeeResponse getEmployeeId(Long id);

    List<EmployeeResponse> getAllEmployees();

    void deleteEmployeeById(Long id);

    EmployeeResponse partialUpdate(Long id, Map<String, Object> updates);

    EmployeeResponse createEmployee(EmployeeRequest employee);
}
