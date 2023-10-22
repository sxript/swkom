package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.DocumentsDocument;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsDocumentRepository extends JpaRepository<DocumentsDocument, Integer> {
}
