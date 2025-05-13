package com.examen.company.shared;

import com.examen.company.domain.valueobject.Gender;
import com.examen.company.infraestructure.web.dto.EmployeeRequest;
import com.examen.company.shared.validator.ValidEmployee;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidatorTest {

    private ValidEmployee validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        validator = new ValidEmployee();
        context = mock(ConstraintValidatorContext.class);

        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext nodeBuilder =
                mock(ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext.class);

        // Simula el builder para addPropertyNode y addConstraintViolation
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(builder.addPropertyNode(anyString())).thenReturn(nodeBuilder);
        when(nodeBuilder.addConstraintViolation()).thenReturn(context);
    }

    @Test
    void testValidEmployeeData() {
        EmployeeRequest employee = new EmployeeRequest(
                "Juan",
                "Carlos",
                "Perez",
                "Gomez",
                30,
                Gender.MALE,
                LocalDate.now().minusYears(30),
                "Developer"
        );

        assertTrue(validator.isValid(employee, context));
    }

    @Test
    void testInvalidNames() {
        EmployeeRequest employee = new EmployeeRequest(
                "Juan123", // inválido
                "Carlos",  // válido
                "Perez",   // válido
                "Gomez",   // válido
                30,
                Gender.MALE,
                LocalDate.now().minusYears(30),
                "Developer"
        );

        assertFalse(validator.isValid(employee, context));
    }

    @Test
    void testInvalidPosition() {
        EmployeeRequest employee = new EmployeeRequest(
                "Ana",
                "Maria",
                "Lopez",
                "Fernandez",
                28,
                Gender.FEMALE,
                LocalDate.now().minusYears(28),
                "Dev#123" // inválido
        );

        assertFalse(validator.isValid(employee, context));
    }

    @Test
    void testNullBirthdate() {
        EmployeeRequest employee = new EmployeeRequest(
                "Ana",
                "Maria",
                "Lopez",
                "Fernandez",
                28,
                Gender.FEMALE,
                null,
                "Developer"
        );

        assertFalse(validator.isValid(employee, context));
    }

    @Test
    void testMismatchedAgeAndBirthdate() {
        EmployeeRequest employee = new EmployeeRequest(
                "Ana",
                "Maria",
                "Lopez",
                "Fernandez",
                20, // edad incorrecta
                Gender.FEMALE,
                LocalDate.now().minusYears(25),
                "Developer"
        );

        assertFalse(validator.isValid(employee, context));
    }
}
