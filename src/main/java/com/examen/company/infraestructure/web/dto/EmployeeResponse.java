package com.examen.company.infraestructure.web.dto;

import com.examen.company.domain.valueobject.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private Long id;

    private String firstName;

    private String midName;

    private String fatherLastName;

    private String motherLastName;

    private int age;

    private Gender gender;

    private LocalDate birthdate;

    private String position;
}
