package com.paperless.integrationTests;

import com.paperless.config.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.annotation.Order;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DocumentServiceTest {
    @Test
    @Order(1)
    void uploadDocumentTest() {
        RestAssured.baseURI = ApiConfig.BASE_URL;
        RestAssured.port = ApiConfig.PORT;
        File testUploadFile = new File("src/test/resources/TestPdf.pdf");
        Response response = given()
                .multiPart("document", testUploadFile)
                .accept(ContentType.JSON)
                .when()
                .post("/api/documents/post_document/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //validate that the response has the status code of 201
        response.then().assertThat().statusCode(200);
    }


    @Test
    void searchDocuments() throws InterruptedException {
        RestAssured.baseURI = ApiConfig.BASE_URL;
        RestAssured.port = ApiConfig.PORT;

        // Wait for 10 seconds to ensure that indexing of the document is complete
        Thread.sleep(10000);

        String response = given()
                .accept(ContentType.JSON)
                .param("query", "TestPdf")
                .when()
                .get("/api/documents/")
                .then()
                .statusCode(200)
                .body("results.title", hasItem("TestPdf.pdf")) // check that first file is in the results
                .extract()
                .body()
                .asString();

        // Parse the response body and extract the id of the test.pdf document
        JsonPath jsonPath = new JsonPath(response);
        List<Map<String, Object>> results = jsonPath.getList("results");
    }
}
