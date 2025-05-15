package com.examen.company.infraestructure.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FieldErrorDetail {

    private String field;

    private String message;
}
