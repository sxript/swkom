package com.paperless.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paperless.persistence.entities.Document;
import com.paperless.persistence.entities.StoragePath;
import com.paperless.persistence.repositories.DocumentsDocumentRepository;
import com.paperless.services.MinIOService;
import com.paperless.services.RabbitMQService;
import com.paperless.services.dto.DocumentDTO;
import com.paperless.services.mapper.DocumentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    private final DocumentsDocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    private final RabbitMQService rabbitMQService;

    private final MinIOService minIOService;

    @Autowired
    public DocumentServiceImpl(DocumentsDocumentRepository documentRepository, DocumentMapper documentMapper, RabbitMQService rabbitMQService, MinIOService minIOService) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.rabbitMQService = rabbitMQService;
        this.minIOService = minIOService;
    }


    @Override
    public void uploadDocument(DocumentDTO documentDTO, MultipartFile file) throws JsonProcessingException {

        Document documentsEntity = documentMapper.toEntity(documentDTO);

        //ToDO: fix Dateiendung

        documentsEntity.setChecksum("checksum");
        documentsEntity.setStorageType(documentDTO.getOriginalFileName().get().split("\\.")[1]);
        documentsEntity.setMimeType(documentDTO.getOriginalFileName().get().split("\\.")[1]);

        String path = documentDTO.getOriginalFileName().get();

        String message = minIOService.getBucketName()+"/"+path;



        StoragePath storagePath = StoragePath.builder().path(message)
        .name(documentDTO.getOriginalFileName().get())
        .isInsensitive(false)
        .match("")
        .matchingAlgorithm(0)
        .build();


//        documentsEntity.setStoragePath(storagePath);

//ToDo: add storagePath to document
//ToDo: updateDocumentMapper and getDocumentMapper
//ToDo: getDocument and updateDocument Routen


        //ToDo: maybe extract the minIOService and rabbitMQService to the Controller
        minIOService.uploadDocument(file, path);

        rabbitMQService.saveInQueue(message);

        documentRepository.save(documentsEntity);

    }


    @Override
    public List<Document> getDocuments() {
       return documentRepository.findAll();
    }


}
