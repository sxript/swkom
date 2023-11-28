package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.AuthUserUserPermissions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserUserPermissionsRepository extends JpaRepository<AuthUserUserPermissions, Integer> {
}
