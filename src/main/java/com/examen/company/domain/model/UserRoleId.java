package com.examen.company.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleId implements Serializable {

    private String username;

    private String role;

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof UserRoleId that)) return false;

        return Objects.equals(this.username, that.username) && Objects.equals(this.role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }
}
