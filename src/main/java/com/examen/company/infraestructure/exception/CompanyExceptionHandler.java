package com.examen.company.infraestructure.exception;

import com.examen.company.infraestructure.web.dto.CompanyError;
import com.examen.company.shared.enums.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CompanyExceptionHandler {

    @ExceptionHandler(CompanyException.class)
    public ResponseEntity<CompanyError> handleCompanyException(CompanyException e) {
        CompanyError companyError = new CompanyError();
        companyError.setErrorCode(e.getErrorCode());
        companyError.setMessage(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(companyError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CompanyError> errorArgument(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        String errorMessage = "The attribute format is incorrect: " + String.join(", ", errors);

        CompanyError error = new CompanyError();
        error.setErrorCode(ErrorCodes.BAD_REQUEST.getCode());
        error.setMessage(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CompanyError> handleReq() {
        CompanyError error = new CompanyError();
        error.setErrorCode(ErrorCodes.BAD_REQUEST.getCode());
        error.setMessage("One of the attribute is incorrect.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
