package com.ocr.paperlessOcr.persistence.repositories;

import com.ocr.paperlessOcr.persistence.entities.AuthGroupPermissions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthGroupPermissionsRepository extends JpaRepository<AuthGroupPermissions, Integer> {
}
