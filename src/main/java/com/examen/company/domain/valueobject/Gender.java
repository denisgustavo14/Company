package com.examen.company.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("male"),
    FEMALE("Female"),
    OTHER("Otro");

    private final String value;
}
