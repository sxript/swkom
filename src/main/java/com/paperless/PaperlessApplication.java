package com.paperless;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
@ComponentScan(
    basePackages = {"com.paperless", "com.paperless.api" , "com.paperless.configuration"},
    nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class PaperlessApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaperlessApplication.class, args);
    }

    @Bean(name = "org.openapitools.PaperlessApplication.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}