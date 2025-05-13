package com.examen.company.infraestructure.web.dto;

import com.examen.company.domain.valueobject.Gender;
import com.examen.company.shared.validator.IValidEmployee;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@IValidEmployee
public class EmployeeRequest {

    private String firstName;

    private String midName;

    private String fatherLastName;

    private String motherLastName;

    private int age;

    private Gender gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    private String position;
}
