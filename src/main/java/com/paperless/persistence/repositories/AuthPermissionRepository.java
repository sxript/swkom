package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.AuthPermission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthPermissionRepository extends JpaRepository<AuthPermission, Integer> {
}
