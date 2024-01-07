package com.ocr.paperlessOcr.persistence.repositories;

import com.ocr.paperlessOcr.persistence.entities.DocumentsLog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsLogRepository extends JpaRepository<DocumentsLog, Integer> {
}
