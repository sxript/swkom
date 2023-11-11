package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsDocumentRepository extends JpaRepository<Document, Integer> {
}
