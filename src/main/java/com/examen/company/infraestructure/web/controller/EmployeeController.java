package com.examen.company.infraestructure.web.controller;

import com.examen.company.application.port.in.EmployeePort;
import com.examen.company.infraestructure.web.dto.DeleteResponse;
import com.examen.company.infraestructure.web.dto.EmployeeRequest;
import com.examen.company.infraestructure.web.dto.EmployeeResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeePort employeePort;

    @GetMapping("/{idEmployee}")
    public EmployeeResponse getEmployeeById(@PathVariable final Long idEmployee) {
        return employeePort.getEmployeeById(idEmployee);
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeePort.getAllEmployees();
    }

    @DeleteMapping("/{idEmployee}")
    public DeleteResponse deleteEmployeeById(@PathVariable final Long idEmployee) {
        employeePort.deleteEmployeeById(idEmployee);
        return new DeleteResponse("Employee with id " + idEmployee + " deleted");
    }

    @PatchMapping("/{idEmployee}")
    public EmployeeResponse updateEmployeeById(@PathVariable final Long idEmployee, final @RequestBody Map<String, Object> updates) {
        return employeePort.partialUpdateEmp(idEmployee, updates);
    }

    @PostMapping("/createEmployee")
    public EmployeeResponse createEmployee(@Valid @RequestBody final EmployeeRequest employee) {
        return employeePort.createEmployee(employee);
    }

}
