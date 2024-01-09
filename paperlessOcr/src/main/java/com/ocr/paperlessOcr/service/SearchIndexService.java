package com.ocr.paperlessOcr.service;

import co.elastic.clients.elasticsearch._types.Result;
import com.ocr.paperlessOcr.persistence.entities.Document;

import java.io.IOException;

public interface SearchIndexService {
    Result indexDocument(Document document) throws IOException;
}
