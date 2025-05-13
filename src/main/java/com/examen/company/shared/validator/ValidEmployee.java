package com.examen.company.shared.validator;

import com.examen.company.infraestructure.web.dto.EmployeeRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;

@Slf4j
public class ValidEmployee implements ConstraintValidator<IValidEmployee, EmployeeRequest> {

    @Override
    public boolean isValid(final EmployeeRequest employeeRequest, final ConstraintValidatorContext context) {
        log.info("Validating employee {}", employeeRequest);

        boolean valid = validateData(employeeRequest, context);

        if (!dateAndAge(employeeRequest, context)) {
            valid = false;
        }

        return valid;
    }

    private void getContext(final ConstraintValidatorContext context, final String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addPropertyNode(" ").addConstraintViolation();
    }

    private boolean validateData(final EmployeeRequest employeeRequest, ConstraintValidatorContext context) {
        String pattern = "^[A-Za-z]+$";
        boolean valid = true;

        if (!employeeRequest.getFirstName().matches(pattern)) {
            getContext(context, "firsName");
            valid = false;
        }
        if (!employeeRequest.getMidName().matches(pattern)) {
            getContext(context, "midName");
            valid = false;
        }
        if (!employeeRequest.getFatherLastName().matches(pattern)) {
            getContext(context, "fathersLastName");
            valid = false;
        }
        if (!employeeRequest.getMotherLastName().matches(pattern)) {
            getContext(context, "mothersLastName");
            valid = false;
        }
        if (!employeeRequest.getPosition().matches("^[A-Za-z ]+$")) {
            valid = false;
        }

        return valid;
    }

    private boolean dateAndAge(EmployeeRequest employeeRequest, ConstraintValidatorContext context) {
        LocalDate birthdate = employeeRequest.getBirthdate();
        int age = employeeRequest.getAge();

        boolean valid = true;

        if (birthdate == null) {
            getContext(context, "birthdate");
            return false;
        }

        LocalDate now = LocalDate.now();
        int calculateAge = Period.between(birthdate, now).getYears();

        if (calculateAge != age) {
            getContext(context, "age");
            valid = false;
        }
        return valid;
    }
}
