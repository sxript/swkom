package com.paperless.integrationTests;

import com.paperless.PaperlessApplication;
import com.paperless.persistence.entities.Document;
import com.paperless.services.MinIOService;
import com.paperless.services.impl.ElasticSearchImpl;
import com.paperless.services.impl.MinIOServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PaperlessApplication.class})
@AutoConfigureMockMvc
class ElasticSearchTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MinIOService minIOService;

    @Autowired
    ElasticSearchImpl elasticSearchService;

    @Test
    void testDocumentUpload() throws Exception {
        // *** ARRANGE *** //
        String title = "Test Document";
        OffsetDateTime created = OffsetDateTime.now();
        Integer documentType = 1;
        Integer correspondent = 123;
        List<Integer> tags = Collections.singletonList(456);

        // mock pdf
        MockMultipartFile file = new MockMultipartFile("document", "maxi.pdf", "application/pdf", "PDF content".getBytes());

        // params for multipart file and req
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("t", title);
        params.add("created", created.toString());
        params.add("document_type", String.valueOf(documentType));
        params.add("correspondent", String.valueOf(correspondent));
        params.add("tags", tags.get(0).toString());
        try {
            // *** ACT *** //
            // mock "upload"
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/documents/post_document/")
                            .file(file)
                            .params(params)
                            .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            String responseBody = result.getResponse().getContentAsString();



            // Find the index of the start and end of the number
            int startIndex = responseBody.indexOf("\"task_id\": \"") + "\"task_id\": \"".length();
            int endIndex = responseBody.indexOf("\"", startIndex);

            // Extract the substring containing the number
            String id = responseBody.substring(startIndex, endIndex);


            // *** ASSERT *** //
            // minio check
            Assertions.assertDoesNotThrow(() -> {
                minIOService.getObjectById(id);
            });


            Optional<Document> esDocument = elasticSearchService.getDocumentById(Integer.parseInt(id));
            Assertions.assertNotNull(esDocument);

            Assertions.assertEquals(200, result.getResponse().getStatus());
        } catch (Exception e){
            System.out.println("Error mocking the upload in test Case");
        }
    }
}
