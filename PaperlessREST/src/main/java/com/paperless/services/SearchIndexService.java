package com.paperless.services;

import com.paperless.persistence.entities.Document;
import com.paperless.services.dto.DocumentDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SearchIndexService {
    List<Document> searchDocument(String query) throws IOException;

}
