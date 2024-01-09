package com.ocr.paperlessOcr.persistence.repositories;

import com.ocr.paperlessOcr.persistence.entities.AuthtokenToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthtokenTokenRepository extends JpaRepository<AuthtokenToken, Long> {
}
