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

import java.time.OffsetDateTime;
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
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue ocrScanQueue;

    @Autowired
    public PaperlessApplication(NativeWebRequest request) {
        this.request = request;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> uploadDocument(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> document) {


      try{
          MultipartFile documentt = document.get(0);
          String name = documentt.getOriginalFilename();
          DocumentDTO documentDTO = new DocumentDTO();


          documentDTO.setTitle(JsonNullable.of(title == null ? name : title));
          documentDTO.setOriginalFileName(JsonNullable.of(name));
          documentDTO.setCreated(created);
          documentDTO.setDocumentType(JsonNullable.of(documentType));
          documentDTO.setTags(JsonNullable.of(tags));
          documentDTO.setCorrespondent(JsonNullable.of(correspondent));

          System.out.println(document.get(0).getOriginalFilename());

          documentService.uploadDocument(documentDTO,document);


          // Convert DocumentDTO to JSON and send to RabbitMQ
          String documentJson = objectMapper.writeValueAsString(documentDTO);
          rabbitTemplate.convertAndSend(ocrScanQueue.getName(), documentJson);
          log.info("Document uploaded and sent to RabbitMQ for OCR processing.");


          return new ResponseEntity<>(HttpStatus.OK);

      }catch (Exception e){
          log.error(e.getMessage());
          return ResponseEntity.internalServerError().build();
      }
    }

//    @Override
//    public ResponseEntity<GetDocuments200Response> getDocuments(Integer page, Integer pageSize, String query, String ordering, List<Integer> tagsIdAll, Integer documentTypeId, Integer storagePathIdIn, Integer correspondentId, Boolean truncateContent) {
//        documentService.getDocuments();
//    }
}
