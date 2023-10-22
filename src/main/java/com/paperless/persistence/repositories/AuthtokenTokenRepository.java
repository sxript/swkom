package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.AuthtokenToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthtokenTokenRepository extends JpaRepository<AuthtokenToken, Long> {
}
