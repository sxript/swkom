package com.ocr.paperlessOcr.persistence.repositories;

import com.ocr.paperlessOcr.persistence.entities.DocumentsTag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsTagRepository extends JpaRepository<DocumentsTag, Integer> {
}
