package com.examen.company.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserEntity {

    @Id
    private String username;

    private String password;

    private String email;

    private boolean locked;

    private boolean disabled;
}
