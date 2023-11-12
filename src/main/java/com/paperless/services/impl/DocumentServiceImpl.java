package com.paperless.services.impl;

import com.paperless.persistence.entities.Document;
import com.paperless.persistence.repositories.DocumentsDocumentRepository;
import com.paperless.services.dto.DocumentDTO;
import com.paperless.services.mapper.DocumentsDocumentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {


    private DocumentsDocumentRepository documentRepository;

    private DocumentsDocumentMapper documentMapper;

    @Autowired
    public DocumentServiceImpl(DocumentsDocumentRepository documentRepository, DocumentsDocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }


    @Override
    public void uploadDocument(DocumentDTO documentDTO, List<MultipartFile> document) {

        documentDTO.setCreated(OffsetDateTime.now());
        documentDTO.setAdded(OffsetDateTime.now());
        documentDTO.setModified(OffsetDateTime.now());
        documentDTO.content("");
        documentDTO.setAdded(OffsetDateTime.now());
        documentDTO.setId(0);


        log.info(documentDTO.toString());

        Document documentsEntity = documentMapper.toEntity(documentDTO);

        log.info(documentsEntity.toString());


        documentsEntity.setChecksum("checksum");
        documentsEntity.setStorageType("pdf");
        documentsEntity.setMimeType("pdf");


        documentRepository.save(documentsEntity);

    }

    @Override
    public List<Document> getDocuments() {
       return documentRepository.findAll();
    }


}
