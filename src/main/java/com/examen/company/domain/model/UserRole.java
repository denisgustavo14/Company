package com.examen.company.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@IdClass(UserRoleId.class)
@NoArgsConstructor
public class UserRole {

    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String role;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime grantedDate;

    @ManyToOne
    @JoinColumn(name= "username", referencedColumnName = "username", insertable = false)
    private UserEntity user;
}
