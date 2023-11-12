package com.paperless.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.paperless.services.dto.DocumentDTO;
import com.paperless.services.dto.gets.GetDocuments200Response;
import com.paperless.services.impl.DocumentServiceImpl;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.core.Queue;

import javax.annotation.Generated;

@Generated(value = "com.paperless.codegen.languages.SpringCodegen", date = "2023-10-10T06:36:40.060738Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.paperlessRestServer.base-path:}")
@CrossOrigin(origins = "http://localhost:8080")
@Slf4j
public class PaperlessApplication implements PaperlessApi {


    private final NativeWebRequest request;

    @Autowired
    DocumentServiceImpl documentService;



    @Autowired
    public PaperlessApplication(NativeWebRequest request) {
        this.request = request;
    }



    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> uploadDocument(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> document) {


        try {
            MultipartFile documentFile = document.get(0);
            String name = documentFile.getOriginalFilename();
            var filename = JsonNullable.of(documentFile.getOriginalFilename());
            var createtionTime = OffsetDateTime.now();


            var fileType = JsonNullable.of(filename.get().split("\\.")[1]);
            byte[] documentContent = documentFile.getBytes();
            var fileContentString = JsonNullable.of(Base64.getEncoder().encodeToString(documentContent));


            DocumentDTO documentDTO = DocumentDTO.builder().title(filename).content(fileContentString).originalFileName(filename).created(createtionTime)
                    .modified(createtionTime).added(createtionTime).build();




            documentService.uploadDocument(documentDTO);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    public static void saveByteArrayToFile(byte[] content, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(content);
        }
    }

//    @Override
//    public ResponseEntity<GetDocuments200Response> getDocuments(Integer page, Integer pageSize, String query, String ordering, List<Integer> tagsIdAll, Integer documentTypeId, Integer storagePathIdIn, Integer correspondentId, Boolean truncateContent) {
//        documentService.getDocuments();
//    }
}
