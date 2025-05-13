package com.examen.company.shared.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEmployee.class)
public @interface IValidEmployee {

    String message() default "Invalid, Check the input data";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
