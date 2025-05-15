package com.examen.company.infraestructure.web.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestFieldError {

    private String errorCode;

    private String message;

    private List<FieldErrorDetail> fieldErrors;
}
