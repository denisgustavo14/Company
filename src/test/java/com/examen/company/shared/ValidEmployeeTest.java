package com.examen.company.shared;

import com.examen.company.domain.valueobject.Gender;
import com.examen.company.infraestructure.web.dto.EmployeeRequest;
import com.examen.company.shared.validator.ValidEmployee;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ValidEmployeeTest {

    private ValidEmployee validator;
    private ConstraintValidatorContext context;
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        // Mock  de MessageSource
        messageSource = mock(MessageSource.class);
        // Siempre devolverá la clave, para que la prueba sea sencilla
        when(messageSource.getMessage(anyString(), any(), any())).thenAnswer(invocation -> invocation.getArgument(0));

        validator = new ValidEmployee(messageSource);

        context = mock(ConstraintValidatorContext.class);

        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext nodeBuilder =
                mock(ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext.class);

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
                "Carlos",
                "Perez",
                "Gomez",
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
                20,
                Gender.FEMALE,
                LocalDate.now().minusYears(25),
                "Developer"
        );
        assertFalse(validator.isValid(employee, context));
    }
}
