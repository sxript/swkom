package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.DocumentsLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsLogRepository extends JpaRepository<DocumentsLog, Integer> {
}
