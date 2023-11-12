package com.paperless.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paperless.persistence.entities.Document;
import com.paperless.persistence.repositories.DocumentsDocumentRepository;
import com.paperless.services.dto.DocumentDTO;
import com.paperless.services.mapper.DocumentsDocumentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue ocrScanQueue;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public DocumentServiceImpl(DocumentsDocumentRepository documentRepository, DocumentsDocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }


    @Override
    public void uploadDocument(DocumentDTO documentDTO) throws JsonProcessingException {

        documentDTO.setCreated(OffsetDateTime.now());
        documentDTO.setAdded(OffsetDateTime.now());
        documentDTO.setModified(OffsetDateTime.now());


        Document documentsEntity = documentMapper.toEntity(documentDTO);

        //ToDO: fix Dateiendung

        documentsEntity.setChecksum("checksum");
        documentsEntity.setStorageType(documentDTO.getOriginalFileName().get().split("\\.")[1]);
        documentsEntity.setMimeType(documentDTO.getOriginalFileName().get().split("\\.")[1]);

        // Convert DocumentDTO to JSON and send to RabbitMQ
        String documentJson = objectMapper.writeValueAsString(documentDTO);
        rabbitTemplate.convertAndSend(ocrScanQueue.getName(), documentJson);
        log.info("Document uploaded and sent to RabbitMQ for OCR processing.");

        documentRepository.save(documentsEntity);

    }

    @Override
    public List<Document> getDocuments() {
       return documentRepository.findAll();
    }


}
