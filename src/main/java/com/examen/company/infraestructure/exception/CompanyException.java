package com.examen.company.infraestructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CompanyException extends RuntimeException {

    private final HttpStatus httpStatus;

    private final String ErrorCode;

    public CompanyException(final HttpStatus httpStatus,  final String ErrorCode, final String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.ErrorCode = ErrorCode;
    }
}
