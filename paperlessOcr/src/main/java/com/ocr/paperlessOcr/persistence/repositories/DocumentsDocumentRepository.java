package com.ocr.paperlessOcr.persistence.repositories;

import com.ocr.paperlessOcr.persistence.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsDocumentRepository extends JpaRepository<Document, Integer> {
}
