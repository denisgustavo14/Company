package com.examen.company.shared.validator;

import com.examen.company.infraestructure.web.dto.EmployeeRequest;
import com.examen.company.shared.constants.FieldsRequest;
import com.examen.company.shared.constants.ValidationsMessages;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;

import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;

@Slf4j
@AllArgsConstructor
public class ValidEmployee implements ConstraintValidator<IValidEmployee, EmployeeRequest> {

    private MessageSource messageSource;

    private static final String PATTERN = "^[A-Za-z]+$";

    @Override
    public boolean isValid(final EmployeeRequest employeeRequest, final ConstraintValidatorContext context) {
        log.info("Validating employee");
        boolean valid = validOne(employeeRequest, context);

        if (!validTwo(employeeRequest, context)) {
            valid = false;
        }

        if (!dateAndAge(employeeRequest, context)) {
            valid = false;
        }

        return valid;
    }

    private void getContext(final ConstraintValidatorContext context, final String field, final String message) {
        context.disableDefaultConstraintViolation();
        String sourceMessage = messageSource.getMessage(message, null, Locale.getDefault());
        context.buildConstraintViolationWithTemplate(sourceMessage).addPropertyNode(field).addConstraintViolation();
    }

    private boolean dateAndAge(final EmployeeRequest employeeRequest, final ConstraintValidatorContext context) {
        LocalDate birthdate = employeeRequest.getBirthdate();
        int age = employeeRequest.getAge();
        boolean valid = true;

        if (birthdate == null) {
            getContext(context, FieldsRequest.BIRTHDATE, ValidationsMessages.EMPLOYEE_NULL_REQUEST);
            return false;
        }

        LocalDate now = LocalDate.now();
        int calculateAge = Period.between(birthdate, now).getYears();

        if (calculateAge != age) {
            getContext(context, FieldsRequest.AGE, ValidationsMessages.EMPLOYEE_INVALID_AGE_REQUEST);
            valid = false;
        }
        return valid;
    }


    private boolean validOne(final EmployeeRequest employeeRequest, final ConstraintValidatorContext context) {
        boolean valid = true;

        if (employeeRequest.getFirstName() == null) {
            getContext(context, FieldsRequest.FIRST_NAME, ValidationsMessages.EMPLOYEE_NULL_REQUEST);
            valid = false;
        } else if (!employeeRequest.getFirstName().matches(PATTERN)) {
            getContext(context, FieldsRequest.FIRST_NAME, ValidationsMessages.EMPLOYEE_BAD_REQUEST);
            valid = false;
        }

        if (employeeRequest.getMidName() == null) {
            getContext(context, FieldsRequest.MID_NAME, ValidationsMessages.EMPLOYEE_NULL_REQUEST);
            valid = false;
        } else if (!employeeRequest.getMidName().matches(PATTERN)) {
            getContext(context, FieldsRequest.MID_NAME, ValidationsMessages.EMPLOYEE_BAD_REQUEST);
            valid = false;
        }

        if (employeeRequest.getFatherLastName() == null) {
            getContext(context, FieldsRequest.FATHER_LAST_NAME, ValidationsMessages.EMPLOYEE_NULL_REQUEST);
            valid = false;
        } else if (!employeeRequest.getFatherLastName().matches(PATTERN)) {
            getContext(context, FieldsRequest.FATHER_LAST_NAME, ValidationsMessages.EMPLOYEE_BAD_REQUEST);
        }

        return valid;
    }

    private boolean validTwo(final EmployeeRequest employeeRequest, final ConstraintValidatorContext context) {
        boolean valid = true;

        if (employeeRequest.getMotherLastName() == null) {
            getContext(context, FieldsRequest.MOTHER_LAST_NAME, ValidationsMessages.EMPLOYEE_NULL_REQUEST);
            valid = false;
        } else if (!employeeRequest.getMotherLastName().matches(PATTERN)) {
            getContext(context, FieldsRequest.MOTHER_LAST_NAME, ValidationsMessages.EMPLOYEE_BAD_REQUEST);
            valid = false;
        }

        if (employeeRequest.getGender() == null) {
            getContext(context, FieldsRequest.GENDER, ValidationsMessages.EMPLOYEE_NULL_REQUEST);
            valid = false;
        } else if (!employeeRequest.getGender().getValue().matches(PATTERN)) {
            getContext(context, FieldsRequest.GENDER, ValidationsMessages.EMPLOYEE_BAD_REQUEST);
        }

        if (employeeRequest.getPosition() == null) {
            getContext(context, FieldsRequest.POSITION, ValidationsMessages.EMPLOYEE_NULL_REQUEST);
            valid = false;
        } else if (!employeeRequest.getPosition().matches("^[A-Za-z ]+$")) {
            getContext(context, FieldsRequest.POSITION, ValidationsMessages.EMPLOYEE_BAD_REQUEST);
            valid = false;
        }

        return valid;
    }
}
