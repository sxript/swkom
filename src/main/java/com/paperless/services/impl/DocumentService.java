package com.paperless.services.impl;

import com.paperless.services.dto.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    void uploadDocument(Document documentDTO, List<MultipartFile> document);
}
