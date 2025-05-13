package com.examen.company.domain.model;

import com.examen.company.domain.valueobject.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String midName;

    private String fatherLastName;

    private String motherLastName;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthdate;

    @Column(name = "POSITION_EMPLOYEE")
    private String position;

    public Employee(String firstName, String midName, String fatherLastName, String motherLastName, int age, Gender gender, LocalDate birthdate, String position) {
        this.firstName = firstName;
        this.midName = midName;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
        this.age = age;
        this.gender = gender;
        this.birthdate = birthdate;
        this.position = position;
    }
}
