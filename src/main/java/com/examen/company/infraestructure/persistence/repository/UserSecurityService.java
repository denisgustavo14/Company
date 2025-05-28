package com.examen.company.infraestructure.persistence.repository;

import com.examen.company.domain.model.UserEntity;
import com.examen.company.infraestructure.exception.CompanyException;
import com.examen.company.shared.enums.ErrorCodes;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepositoryJPA userRepositoryJPA;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepositoryJPA.findById(username).orElseThrow(() -> new CompanyException(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND.getCode(), ErrorCodes.NOT_FOUND.getMessage() + " User with username " + username + " not found"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("ADMIN")
                .accountLocked(user.isLocked())
                .disabled(user.isDisabled())
                .build();
    }
}
