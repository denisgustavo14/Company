package com.examen.company.infraestructure.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyError {

    private String errorCode;

    private String message;
}
