package com.examen.company.infraestructure.persistence.repository;

import com.examen.company.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJPA extends JpaRepository<UserEntity, String> {

}
