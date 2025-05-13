package com.examen.company.infraestructure.persistence.mapper;

import com.examen.company.domain.model.Employee;
import com.examen.company.infraestructure.web.dto.EmployeeRequest;
import com.examen.company.infraestructure.web.dto.EmployeeResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {

    public EmployeeResponse toEmployeeResponse(Employee employee) {
        return new EmployeeResponse(employee.getId(), employee.getFirstName(), employee.getMidName(), employee.getFatherLastName(), employee.getMotherLastName(),
                employee.getAge(), employee.getGender(), employee.getBirthdate(), employee.getPosition());
    }

    public List<EmployeeResponse> toEmployeeResponseList(List<Employee> employees) {
        return employees.stream().map(this::toEmployeeResponse).toList();
    }

    public Employee toEmployee(EmployeeRequest employeeRequest) {
        return new Employee(employeeRequest.getFirstName(), employeeRequest.getMidName(), employeeRequest.getFatherLastName(), employeeRequest.getMotherLastName(),
                employeeRequest.getAge(), employeeRequest.getGender(), employeeRequest.getBirthdate(), employeeRequest.getPosition());
    }
}
