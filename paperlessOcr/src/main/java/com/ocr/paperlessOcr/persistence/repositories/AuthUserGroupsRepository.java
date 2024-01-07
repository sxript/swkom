package com.ocr.paperlessOcr.persistence.repositories;

import com.ocr.paperlessOcr.persistence.entities.AuthUserGroups;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserGroupsRepository extends JpaRepository<AuthUserGroups, Integer> {
}
