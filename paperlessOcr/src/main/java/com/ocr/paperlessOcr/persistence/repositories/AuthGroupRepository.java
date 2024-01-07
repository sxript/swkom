package com.ocr.paperlessOcr.persistence.repositories;

import com.ocr.paperlessOcr.persistence.entities.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthGroupRepository extends JpaRepository<AuthGroup, Integer> {
}
