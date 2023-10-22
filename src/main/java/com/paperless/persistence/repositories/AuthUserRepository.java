package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
}
