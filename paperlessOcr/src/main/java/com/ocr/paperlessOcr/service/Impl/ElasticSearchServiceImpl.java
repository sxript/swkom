package com.ocr.paperlessOcr.service.Impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.ocr.paperlessOcr.configuration.ElasticSearchConfig;
import com.ocr.paperlessOcr.persistence.entities.Document;
import com.ocr.paperlessOcr.service.SearchIndexService;
import com.ocr.paperlessOcr.service.dto.DocumentDTO;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Primary
@Slf4j
public class ElasticSearchServiceImpl implements SearchIndexService {

    private final ElasticsearchClient esClient;

    @Autowired
    public ElasticSearchServiceImpl(ElasticsearchClient esClient) throws IOException {
        this.esClient = esClient;

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

        DocumentDTO documentdto = DocumentDTO.builder().id(document.getId()).content(JsonNullable.of(document.getContent()))
                .title(JsonNullable.of(document.getTitle())).build();


        IndexResponse response = esClient.index(i -> i
                .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                .id(documentdto.getId().toString())
                .document(documentdto)
        );

        return response.result();

    }
}
