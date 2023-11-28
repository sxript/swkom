package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.DocumentsUisettings;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UISettingsRepository extends JpaRepository<DocumentsUisettings, Integer> {
}
