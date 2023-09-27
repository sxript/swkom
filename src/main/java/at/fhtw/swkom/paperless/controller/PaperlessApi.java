/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.1.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.services.dto.Correspondent;
import org.springframework.format.annotation.DateTimeFormat;
import at.fhtw.swkom.paperless.services.dto.DocTag;
import at.fhtw.swkom.paperless.services.dto.Document;
import at.fhtw.swkom.paperless.services.dto.DocumentType;
import at.fhtw.swkom.paperless.services.dto.NewCorrespondent;
import at.fhtw.swkom.paperless.services.dto.NewDocumentType;
import at.fhtw.swkom.paperless.services.dto.NewTag;
import java.time.OffsetDateTime;
import at.fhtw.swkom.paperless.services.dto.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.codec.multipart.Part;

import jakarta.validation.Valid;

import java.util.List;

import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Tag(name = "Login", description = "the Login API")
public interface PaperlessApi {

    /**
     * GET /api
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "apiGet",
        tags = { "Login" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api"
    )
    
    default Mono<ResponseEntity<Void>> apiGet(
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * GET /api/search/autocomplete
     *
     * @param term  (optional)
     * @param limit  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "autoComplete",
        tags = { "Search" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/search/autocomplete"
    )
    
    default Mono<ResponseEntity<Void>> autoComplete(
        @Parameter(name = "term", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "term", required = false) String term,
        @Parameter(name = "limit", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false) Integer limit,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * POST /api/correspondents
     *
     * @param newCorrespondent  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "createCorrespondent",
        tags = { "Correspondents" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/correspondents",
        consumes = { "application/json", "text/json", "application/*+json" }
    )
    
    default Mono<ResponseEntity<Void>> createCorrespondent(
        @Parameter(name = "NewCorrespondent", description = "") @Valid @RequestBody(required = false) Mono<NewCorrespondent> newCorrespondent,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(newCorrespondent).then(Mono.empty());

    }


    /**
     * POST /api/document_types
     *
     * @param newDocumentType  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "createDocumentType",
        tags = { "DocumentTypes" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/document_types",
        consumes = { "application/json", "text/json", "application/*+json" }
    )
    
    default Mono<ResponseEntity<Void>> createDocumentType(
        @Parameter(name = "NewDocumentType", description = "") @Valid @RequestBody(required = false) Mono<NewDocumentType> newDocumentType,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(newDocumentType).then(Mono.empty());

    }


    /**
     * POST /api/tags
     *
     * @param newTag  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "createTag",
        tags = { "Tags" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/tags",
        consumes = { "application/json", "text/json", "application/*+json" }
    )
    
    default Mono<ResponseEntity<Void>> createTag(
        @Parameter(name = "NewTag", description = "") @Valid @RequestBody(required = false) Mono<NewTag> newTag,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(newTag).then(Mono.empty());

    }


    /**
     * DELETE /api/correspondents/{id}
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "deleteCorrespondent",
        tags = { "Correspondents" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/correspondents/{id}"
    )
    
    default Mono<ResponseEntity<Void>> deleteCorrespondent(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * DELETE /api/documents/{id}
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "deleteDocument",
        tags = { "Documents" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/documents/{id}"
    )
    
    default Mono<ResponseEntity<Void>> deleteDocument(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * DELETE /api/document_types/{id}
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "deleteDocumentType",
        tags = { "DocumentTypes" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/document_types/{id}"
    )
    
    default Mono<ResponseEntity<Void>> deleteDocumentType(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * DELETE /api/tags/{id}
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "deleteTag",
        tags = { "Tags" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/tags/{id}"
    )
    
    default Mono<ResponseEntity<Void>> deleteTag(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * GET /api/documents/{id}/download
     *
     * @param id  (required)
     * @param original  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "downloadDocument",
        tags = { "Documents" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/documents/{id}/download"
    )
    
    default Mono<ResponseEntity<Void>> downloadDocument(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(name = "original", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "original", required = false) Boolean original,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * GET /api/correspondents
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "getCorrespondents",
        tags = { "Correspondents" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/correspondents"
    )
    
    default Mono<ResponseEntity<Void>> getCorrespondents(
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * GET /api/documents/{id}/metadata
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "getDocumentMetadata",
        tags = { "Documents" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/documents/{id}/metadata"
    )
    
    default Mono<ResponseEntity<Void>> getDocumentMetadata(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * GET /api/documents/{id}/preview
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "getDocumentPreview",
        tags = { "Documents" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/documents/{id}/preview"
    )
    
    default Mono<ResponseEntity<Void>> getDocumentPreview(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * GET /api/documents/{id}/thumb
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "getDocumentThumb",
        tags = { "Documents" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/documents/{id}/thumb"
    )
    
    default Mono<ResponseEntity<Void>> getDocumentThumb(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * GET /api/document_types
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "getDocumentTypes",
        tags = { "DocumentTypes" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/document_types"
    )
    
    default Mono<ResponseEntity<Void>> getDocumentTypes(
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * GET /api/documents
     *
     * @param page  (optional)
     * @param pageSize  (optional)
     * @param query  (optional)
     * @param ordering  (optional)
     * @param tagsIdAll  (optional)
     * @param documentTypeId  (optional)
     * @param correspondentId  (optional)
     * @param truncateContent  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "getDocuments",
        tags = { "Documents" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/documents"
    )
    
    default Mono<ResponseEntity<Void>> getDocuments(
        @Parameter(name = "Page", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "Page", required = false) Integer page,
        @Parameter(name = "page_size", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page_size", required = false) Integer pageSize,
        @Parameter(name = "query", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "query", required = false) String query,
        @Parameter(name = "ordering", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "ordering", required = false) String ordering,
        @Parameter(name = "tags__id__all", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "tags__id__all", required = false) List<Integer> tagsIdAll,
        @Parameter(name = "document_type__id", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "document_type__id", required = false) Integer documentTypeId,
        @Parameter(name = "correspondent__id", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "correspondent__id", required = false) Integer correspondentId,
        @Parameter(name = "truncate_content", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "truncate_content", required = false) Boolean truncateContent,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * GET /api/tags
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "getTags",
        tags = { "Tags" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/tags"
    )
    
    default Mono<ResponseEntity<Void>> getTags(
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * POST /api/token
     *
     * @param userInfo  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "getToken",
        tags = { "Login" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/token",
        consumes = { "application/json", "text/json", "application/*+json" }
    )
    
    default Mono<ResponseEntity<Void>> getToken(
        @Parameter(name = "UserInfo", description = "") @Valid @RequestBody(required = false) Mono<UserInfo> userInfo,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(userInfo).then(Mono.empty());

    }


    /**
     * POST /api
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "root",
        tags = { "Login" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api"
    )
    
    default Mono<ResponseEntity<Void>> root(
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * GET /api/statistics
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "statistics",
        tags = { "Login" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/statistics"
    )
    
    default Mono<ResponseEntity<Void>> statistics(
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * PUT /api/correspondents/{id}
     *
     * @param id  (required)
     * @param correspondent  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "updateCorrespondent",
        tags = { "Correspondents" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/correspondents/{id}",
        consumes = { "application/json", "text/json", "application/*+json" }
    )
    
    default Mono<ResponseEntity<Void>> updateCorrespondent(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(name = "Correspondent", description = "") @Valid @RequestBody(required = false) Mono<Correspondent> correspondent,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(correspondent).then(Mono.empty());

    }


    /**
     * PUT /api/documents/{id}
     *
     * @param id  (required)
     * @param document  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "updateDocument",
        tags = { "Documents" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/documents/{id}",
        consumes = { "application/json", "text/json", "application/*+json" }
    )
    
    default Mono<ResponseEntity<Void>> updateDocument(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(name = "Document", description = "") @Valid @RequestBody(required = false) Mono<Document> document,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(document).then(Mono.empty());

    }


    /**
     * PUT /api/document_types/{id}
     *
     * @param id  (required)
     * @param documentType  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "updateDocumentType",
        tags = { "DocumentTypes" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/document_types/{id}",
        consumes = { "application/json", "text/json", "application/*+json" }
    )
    
    default Mono<ResponseEntity<Void>> updateDocumentType(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(name = "DocumentType", description = "") @Valid @RequestBody(required = false) Mono<DocumentType> documentType,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(documentType).then(Mono.empty());

    }


    /**
     * PUT /api/tags/{id}
     *
     * @param id  (required)
     * @param docTag  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "updateTag",
        tags = { "Tags" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/tags/{id}",
        consumes = { "application/json", "text/json", "application/*+json" }
    )
    
    default Mono<ResponseEntity<Void>> updateTag(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(name = "DocTag", description = "") @Valid @RequestBody(required = false) Mono<DocTag> docTag,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(docTag).then(Mono.empty());

    }


    /**
     * POST /api/documents/post_document
     *
     * @param title  (optional)
     * @param created  (optional)
     * @param documentType  (optional)
     * @param tags  (optional)
     * @param correspondent  (optional)
     * @param document  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "uploadDocument",
        tags = { "Documents" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/documents/post_document",
        consumes = { "multipart/form-data" }
    )
    
    default Mono<ResponseEntity<Void>> uploadDocument(
        @Parameter(name = "title", description = "") @Valid @RequestParam(value = "title", required = false) String title,
        @Parameter(name = "created", description = "") @Valid @RequestParam(value = "created", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime created,
        @Parameter(name = "document_type", description = "") @Valid @RequestParam(value = "document_type", required = false) Integer documentType,
        @Parameter(name = "tags", description = "") @Valid @RequestPart(value = "tags", required = false) List<Integer> tags,
        @Parameter(name = "correspondent", description = "") @Valid @RequestParam(value = "correspondent", required = false) Integer correspondent,
        @Parameter(name = "document", description = "") @RequestPart(value = "document", required = false) List<Flux<Part>> document,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }

}