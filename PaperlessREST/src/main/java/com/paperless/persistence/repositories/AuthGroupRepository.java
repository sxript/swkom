package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthGroupRepository extends JpaRepository<AuthGroup, Integer> {
}
