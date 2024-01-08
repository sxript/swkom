package com.paperless.services.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paperless.configuration.ElasticSearchConfig;
import com.paperless.persistence.entities.Document;
import com.paperless.persistence.repositories.DocumentsDocumentRepository;
import com.paperless.services.SearchIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
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

        System.out.println(query);
        System.out.println("Query");

        SearchResponse<ObjectNode> response = esClient.search(s -> s
                        .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                        .size(1000)
                        .query(q ->q.multiMatch(m -> m
                                .fields("content", "title").query(query))),
                ObjectNode.class
        );


        if (response.hits().total().value() != 0) {
            System.out.println("Found {} documents" + response.hits().total().value());
        } else {
            System.out.println("No documents found");
        }

        return extractDocuments(response.hits());

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
            System.out.println(documentId);
//            Optional<Document> document = documentRepository.findById(documentId);
            documentRepository.findById(documentId).ifPresent(documentEntities::add);
        }

        return documentEntities;
    }
}
