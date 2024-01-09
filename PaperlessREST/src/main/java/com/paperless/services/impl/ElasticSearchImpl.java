package com.paperless.services.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paperless.configuration.ElasticSearchConfig;
import com.paperless.persistence.entities.Document;
import com.paperless.persistence.repositories.DocumentsDocumentRepository;
import com.paperless.services.SearchIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class ElasticSearchImpl implements SearchIndexService {

    private final ElasticsearchClient esClient;
    private final DocumentsDocumentRepository documentRepository;

    @Autowired
    public ElasticSearchImpl(ElasticsearchClient esClient, DocumentsDocumentRepository documentRepository) throws IOException {
        this.esClient = esClient;
        this.documentRepository = documentRepository;
    }

    @Override
    public List<Document> searchDocument(String query) throws IOException {

        SearchResponse<ObjectNode> response = esClient.search(s -> s
                        .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                        .size(1000)
                        .query(q -> q.multiMatch(m -> m
                                .fields("content", "title").query(query))),
                ObjectNode.class
        );

//ToDo: Logger
        if (response.hits().total().value() != 0) {
            log.info("Found {} documents" , response.hits().total().value());
        } else {
            log.error("No documents found");
        }

        return extractDocuments(response.hits());

    }

    @Override
    public Optional<Document> getDocumentById(int id) {
        try {
            GetResponse<Document> response = esClient.get(g -> g
                            .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                            .id(String.valueOf(id)),
                    Document.class
            );
            return (response.found() && response.source()!=null) ? Optional.of(response.source()) : Optional.empty();
        } catch (IOException e) {
            log.error("Failed to get document id=" + id + " from elasticsearch: " + e);
            return Optional.empty();
        }
    }

    private List<Document> extractDocuments(HitsMetadata<ObjectNode> hitsMetadata) {
        List<ObjectNode> documents = new ArrayList<>();

        //get hits
        for (Hit<ObjectNode> hit : hitsMetadata.hits()) {
            documents.add(hit.source());
        }

        //extract ids
        List<Integer> documentIds = new ArrayList<>();
        for (ObjectNode document : documents) {
            documentIds.add(document.get("id").asInt());
        }

        //get documents from repository
        List<Document> documentEntities = new ArrayList<>();
        for (Integer documentId : documentIds) {
            documentRepository.findById(documentId).ifPresent(documentEntities::add);
        }

        return documentEntities;
    }
}
