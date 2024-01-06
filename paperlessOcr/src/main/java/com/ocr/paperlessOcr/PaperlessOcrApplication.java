package com.ocr.paperlessOcr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;



@ComponentScan(
		basePackages = {"com.ocr.paperlessOcr", "com.ocr.paperlessOcr.configuration", "com.ocr.paperlessOcr.service" },
		nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)


@SpringBootApplication(
		nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class PaperlessOcrApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaperlessOcrApplication.class, args);
	}

}
