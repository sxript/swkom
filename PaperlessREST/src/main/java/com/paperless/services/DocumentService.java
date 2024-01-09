package com.paperless.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paperless.persistence.entities.Document;
import com.paperless.services.dto.DocumentDTO;
import com.paperless.services.dto.gets.GetDocuments200Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    boolean uploadDocument(DocumentDTO documentDTO, MultipartFile file) throws JsonProcessingException;
    ResponseEntity<GetDocuments200Response> getDocuments(Integer page, Integer pageSize, String query, String ordering, List<Integer> tagsIdAll, Integer documentTypeId, Integer storagePathIdIn, Integer correspondentId, Boolean truncateContent) throws IOException;

}
