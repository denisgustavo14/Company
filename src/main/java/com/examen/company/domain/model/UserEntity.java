package com.examen.company.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    private String username;

    private String password;

    private String email;

    private boolean locked;

    private boolean disabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> roles;
}
