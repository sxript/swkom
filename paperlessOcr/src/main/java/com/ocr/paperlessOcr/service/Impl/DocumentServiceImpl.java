package com.ocr.paperlessOcr.service.Impl;

import com.ocr.paperlessOcr.persistence.entities.Document;
import com.ocr.paperlessOcr.persistence.repositories.DocumentsDocumentRepository;
import com.ocr.paperlessOcr.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private  final DocumentsDocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentsDocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void saveDocument(Document document) {
        documentRepository.save(document);
    }

    @Override
    public Optional<Document> getById(String id) {
        return documentRepository.findById(Integer.parseInt(id));
    }
}
