package com.ocr.paperlessOcr.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.ocr.paperlessOcr.configuration.ElasticSearchConfig;
import com.ocr.paperlessOcr.persistence.entities.Document;
import com.ocr.paperlessOcr.service.dto.DocumentDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.io.IOException;

@Component
public class ElasticSearchService implements SearchIndexService {

    private final ElasticsearchClient esClient;

    @Autowired
    public ElasticSearchService(ElasticsearchClient esClient) throws IOException {
        this.esClient = esClient;


        System.out.println("line 22");
        if (!esClient.indices().exists(
                i -> i.index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
        ).value()) {
            esClient.indices().create(c -> c
                    .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
            );
        }
    }

    @Override
    public Result indexDocument(Document document) throws IOException {
        System.out.println("line 34");

        DocumentDTO documentdto = DocumentDTO.builder().id(document.getId()).content(JsonNullable.of(document.getContent())).build();



        System.out.println("line 42");

        IndexResponse response = esClient.index(i -> i
                .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                .id(documentdto.getId().toString())
                .document(documentdto)
        );
        String logMsg = "Indexed document " + documentdto.getId() + ": result=" + response.result() + ", index=" + response.index();
        if (response.result() != Result.Created && response.result() != Result.Updated)
            System.out.println("Failed to " + logMsg);
        else
            System.out.println("Successfully " + logMsg);
        return response.result();

    }
}
