package com.paperless.services.impl;

import com.paperless.persistence.entities.DocumentsDocument;
import com.paperless.persistence.repositories.DocumentsDocumentRepository;
import com.paperless.services.dto.Document;
import com.paperless.services.mapper.DocumentsDocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {


    private DocumentsDocumentRepository documentRepository;

    private DocumentsDocumentMapper documentMapper;

    @Autowired
    public DocumentServiceImpl(DocumentsDocumentRepository documentRepository, DocumentsDocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }


    @Override
    public void uploadDocument(Document documentDTO, List<MultipartFile> document) {

        documentDTO.setCreated(OffsetDateTime.now());
        documentDTO.setAdded(OffsetDateTime.now());
        documentDTO.setModified(OffsetDateTime.now());
        documentDTO.content("");
        documentDTO.setAdded(OffsetDateTime.now());

        System.out.println(documentDTO);

        DocumentsDocument documentsEntity = documentMapper.toEntity(documentDTO);



        documentsEntity.setChecksum("checksum");
        documentsEntity.setStorageType("pdf");
        documentsEntity.setMimeType("pdf");


        documentRepository.save(documentsEntity);

    }


}
