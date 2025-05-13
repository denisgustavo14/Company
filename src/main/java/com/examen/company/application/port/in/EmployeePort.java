package com.examen.company.application.port.in;

import com.examen.company.infraestructure.web.dto.EmployeeRequest;
import com.examen.company.infraestructure.web.dto.EmployeeResponse;

import java.util.List;
import java.util.Map;

public interface EmployeePort {

    EmployeeResponse getEmployeeById(Long id);

    List<EmployeeResponse> getAllEmployees();

    void deleteEmployeeById(Long id);

    EmployeeResponse partialUpdateEmp(Long id, Map<String, Object> updates);

    EmployeeResponse createEmployee(EmployeeRequest employee);
}
