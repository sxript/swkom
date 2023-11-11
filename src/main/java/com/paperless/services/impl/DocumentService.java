package com.paperless.services.impl;

import com.paperless.persistence.entities.Document;
import com.paperless.services.dto.DocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    void uploadDocument(DocumentDTO documentDTO, List<MultipartFile> document);

    List<Document> getDocuments();
}
