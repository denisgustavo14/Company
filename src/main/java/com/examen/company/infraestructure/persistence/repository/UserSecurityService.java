package com.examen.company.infraestructure.persistence.repository;

import com.examen.company.domain.model.UserEntity;
import com.examen.company.domain.model.UserRole;
import com.examen.company.infraestructure.exception.CompanyException;
import com.examen.company.shared.enums.ErrorCodes;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("prod")
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepositoryJPA userRepositoryJPA;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepositoryJPA.findById(username).orElseThrow(() -> new CompanyException(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND.getCode(), ErrorCodes.NOT_FOUND.getMessage() + " User with username " + username + " not found"));

        String[] roles = user.getRoles().stream().map(UserRole::getRole).toArray(String[]::new);

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(this.grantedAuthorities(roles))
                .accountLocked(user.isLocked())
                .disabled(user.isDisabled())
                .build();
    }

    private String[] getAuthoritiesForRole(String role) {
        if ("ADMIN".equals(role)) {
            return new String[]{"update_employee"};
        }
        return new String[]{};
    }

    private List<GrantedAuthority> grantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            for (String authority : getAuthoritiesForRole(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }
}
