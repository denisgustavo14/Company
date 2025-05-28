package com.examen.company.infraestructure.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Profile("dev")
@Configuration
@AllArgsConstructor
public class SecurityDevConfig {

    private CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource())) //habilita CORS //.cors(withDefaults()) es una versión abreviada que permite usar @CrossOrigin, sin tener que declarar un CorsConfigurationSource.
                .csrf(AbstractHttpConfigurer::disable) //desactiva CSRF para permitir POST, PUT, DELETE en APIs REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/api-docs/**",
                                "/employees/*").permitAll()
                        .anyRequest().authenticated() //cualquier ruta que no esté listada arriba requiere autenticacion
                ).httpBasic(httpBasic -> { //activa Basic Auth
                });

        return http.build();
    }

    @Bean
    public UserDetailsService memoryUsers() { //sirve para crear usuarios en memoria (sin usar db), util: pruebas unitarias, demos, implementando Basic Auth
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin") //{noop} es para no configurar el bean de la interface passwordEncoder
                .roles("ADMIN")
                .build();

        UserDetails customer = User.builder()
                .username("customer")
                .password("{noop}customer")
                .roles("CUSTOMER")
                .build();

        return new InMemoryUserDetailsManager(admin, customer);
    }

}
