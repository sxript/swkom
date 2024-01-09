package com.ocr.paperlessOcr.persistence.repositories;

import com.ocr.paperlessOcr.persistence.entities.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthPermissionRepository extends JpaRepository<AuthPermission, Integer> {
}
