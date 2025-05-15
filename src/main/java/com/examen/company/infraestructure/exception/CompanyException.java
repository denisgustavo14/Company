package com.examen.company.infraestructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CompanyException extends RuntimeException {

    private final HttpStatus httpStatus;

    private final String errorCode;

    public CompanyException(final HttpStatus httpStatus,  final String errorCode, final String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}
