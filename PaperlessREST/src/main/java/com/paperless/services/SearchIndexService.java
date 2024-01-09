package com.paperless.services;

import com.paperless.persistence.entities.Document;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SearchIndexService {
    List<Document> searchDocument(String query) throws IOException;

    Optional<Document> getDocumentById(int id);
}
