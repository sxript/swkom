package com.paperless.integrationTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.annotation.Order;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DocumentServiceTest {


    @Test
    @Order(1)
    public void uploadDocumentTest() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8088;
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
    public void searchDocuments() throws InterruptedException {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8088;

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

        System.out.println(response);

        // Parse the response body and extract the id of the test.pdf document
        JsonPath jsonPath = new JsonPath(response);
        List<Map<String, Object>> results = jsonPath.getList("results");



    }



}
