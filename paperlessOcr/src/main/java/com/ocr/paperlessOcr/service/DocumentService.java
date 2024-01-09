package com.ocr.paperlessOcr.service;

import com.ocr.paperlessOcr.persistence.entities.Document;

import java.util.Optional;

public interface DocumentService {
    void saveDocument(Document document);

    Optional<Document> getById(String id);
}
