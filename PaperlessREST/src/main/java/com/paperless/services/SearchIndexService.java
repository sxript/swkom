package com.paperless.services;

import com.paperless.persistence.entities.Document;

import java.io.IOException;
import java.util.List;

public interface SearchIndexService {
    List<Document> searchDocument(String query) throws IOException;

}
