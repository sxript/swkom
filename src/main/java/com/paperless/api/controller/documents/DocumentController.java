package com.paperless.api.controller.documents;

import com.paperless.api.PaperlessApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;

public class DocumentController implements PaperlessApi {
    @Operation(
            operationId = "uploadDocument",
            tags = {"Documents"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/api/documents/post_document/",
            consumes = {"multipart/form-data"}
    )
    @Override
    public ResponseEntity<Void> uploadDocument(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> document) {

        System.out.println(document);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
