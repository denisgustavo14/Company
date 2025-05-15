package com.examen.company.infraestructure.persistence.repository;

import com.examen.company.application.port.out.EmployeeRepository;
import com.examen.company.domain.model.Employee;
import com.examen.company.domain.valueobject.Gender;
import com.examen.company.infraestructure.exception.CompanyException;
import com.examen.company.infraestructure.persistence.mapper.EmployeeMapper;
import com.examen.company.infraestructure.web.dto.EmployeeRequest;
import com.examen.company.infraestructure.web.dto.EmployeeResponse;
import com.examen.company.shared.constants.FieldsRequest;
import com.examen.company.shared.enums.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepository {

    private final EmployeeRepositoryJPA employeeRepositoryJPA;

    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponse getEmployeeId(final Long id) {
        log.info("Getting employee with id {}", id);
        return employeeRepositoryJPA.findEmployeeById(id).map(employeeMapper::toEmployeeResponse).orElseThrow(
                () -> new CompanyException(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND.getCode(), ErrorCodes.NOT_FOUND.getMessage() + " Employee with id " + id + " not found")
        );
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        try {
            log.info("Getting all employees");
            return employeeMapper.toEmployeeResponseList(employeeRepositoryJPA.findAll());
        } catch (Exception e) {
            log.error("Error getting all employees ", e);
            throw new CompanyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.INTERNAL_SERVER_ERROR.getCode(), ErrorCodes.INTERNAL_SERVER_ERROR.getMessage());
        }
    }

    @Override
    public void deleteEmployeeById(final Long id) {
        try {
            log.info("Deleting employee with id {}", id);
            this.getEmployeeId(id);
            employeeRepositoryJPA.deleteById(id);
        } catch (CompanyException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error deleting employee with id {}", id, e);
            throw new CompanyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.INTERNAL_SERVER_ERROR.getCode(), ErrorCodes.INTERNAL_SERVER_ERROR.getMessage() + " Error deleting employee with id " + id);
        }
    }

    @Override
    public EmployeeResponse partialUpdate(final Long id, final Map<String, Object> updates) {
        log.info("Updating employee with id {}", id);
        Employee employee = employeeRepositoryJPA.findEmployeeById(id).orElseThrow(
                () -> new CompanyException(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND.getCode(), ErrorCodes.NOT_FOUND.getMessage() + " Employee with id " + id + " not found")
        );

        updates.forEach((key, value) -> applyUpdate(employee, key, value));

        Employee newEmployee = employeeRepositoryJPA.save(employee);
        return employeeMapper.toEmployeeResponse(newEmployee);
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        log.debug("Creating employee with id {}", employeeRequest);
        Employee employee = employeeMapper.toEmployee(employeeRequest);
        employeeRepositoryJPA.save(employee);
        return this.getEmployeeId(employee.getId());
    }

    private void applyUpdate(final Employee employee, final String key, final Object value) {
        log.debug("Updating employee with id {} with key {} and value {}", employee.getId(), key, value);
        switch (key) {
            case FieldsRequest.FIRST_NAME -> employee.setFirstName((String) value);
            case FieldsRequest.MID_NAME -> employee.setMidName((String) value);
            case FieldsRequest.FATHER_LAST_NAME -> employee.setFatherLastName((String) value);
            case FieldsRequest.MOTHER_LAST_NAME -> employee.setMotherLastName((String) value);
            case FieldsRequest.AGE -> employee.setAge((Integer) value);
            case FieldsRequest.GENDER -> employee.setGender(Gender.valueOf((String) value));
            case FieldsRequest.BIRTHDATE -> employee.setBirthdate((LocalDate) value);
            case FieldsRequest.POSITION -> employee.setPosition((String) value);
            default -> throw new CompanyException(HttpStatus.BAD_REQUEST, ErrorCodes.BAD_REQUEST.getCode(), ErrorCodes.BAD_REQUEST.getMessage() + " Invalid key " + key);
        }
    }


}