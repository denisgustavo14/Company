package com.examen.company.application.usecase;

import com.examen.company.application.port.in.EmployeePort;
import com.examen.company.application.port.out.EmployeeRepository;
import com.examen.company.infraestructure.web.dto.EmployeeRequest;
import com.examen.company.infraestructure.web.dto.EmployeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeePort {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        return employeeRepository.getEmployeeId(id);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public EmployeeResponse partialUpdateEmp(Long id, Map<String, Object> updates) {
       return employeeRepository.partialUpdate(id, updates);
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employee) {
        return employeeRepository.createEmployee(employee);
    }

}
