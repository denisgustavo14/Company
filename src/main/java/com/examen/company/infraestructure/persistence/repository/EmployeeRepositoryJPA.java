package com.examen.company.infraestructure.persistence.repository;

import com.examen.company.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryJPA extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeById(Long id);

    List<Employee> findAll();

    void deleteById(Long id);

    Employee save(Employee employee);
}
