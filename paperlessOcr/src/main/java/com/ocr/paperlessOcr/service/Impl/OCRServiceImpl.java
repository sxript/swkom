package com.ocr.paperlessOcr.service.Impl;

import com.ocr.paperlessOcr.persistence.entities.Document;

import com.ocr.paperlessOcr.service.OCRService;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@Slf4j
public class OCRServiceImpl implements OCRService {
    private final MinioClient minioClient;
    private final ElasticSearchServiceImpl searchIndexService;
    private final DocumentServiceImpl documentService;

    @Autowired
    public OCRServiceImpl(MinioClient minioClient, ElasticSearchServiceImpl searchIndexService, DocumentServiceImpl documentService) {
        this.minioClient = minioClient;
        this.searchIndexService = searchIndexService;
        this.documentService = documentService;
    }

    @Override
    public void performOCR(String id) {

        var data = getPdfData(id);

        try {
            var image = convertPdfToImages(data);
            ITesseract tesseract = new Tesseract();
            String result = tesseract.doOCR(image);

            Optional<Document> document = documentService.getById(id);
            document.get().setContent(result);
            document.get().setModified(OffsetDateTime.now());

            //ToDo: check the Optional

            documentService.saveDocument(document.get());
            // do ElasticSearch indexing
            try {
                searchIndexService.indexDocument(document.get());
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    public byte[] getPdfData(String storageId) {


        Optional<Document> document = documentService.getById(storageId);


        String[] bucketAndFileName = extractBucketAndFileName(document.get().getStoragePath().getPath());

        if (bucketAndFileName == null) return null;

        String bucketName = bucketAndFileName[0];
        String fileName = bucketAndFileName[1];

        try (InputStream stream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build())) {

            return stream.readAllBytes();
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
        }

        return null;
    }

    private String[] extractBucketAndFileName(String pdfFileName) {
        // Assuming the format is "bucketName/path/to/file.pdf"
        String[] parts = pdfFileName.split("/", 2);

        if (parts.length > 1) {
            return parts;
        } else {
            return null;
        }
    }


    private String extractTextFromPdf(String filePath) throws IOException {
        PDDocument document = Loader.loadPDF(new File(filePath));
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String pdfText = pdfTextStripper.getText(document);
        document.close();
        return pdfText;
    }

    public BufferedImage convertPdfToImages(byte[] pdfData) throws IOException {
        BufferedImage images = null;

        try (PDDocument document = Loader.loadPDF(pdfData)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
                BufferedImage image = pdfRenderer.renderImage(pageIndex);
                images = image;
            }
        }

        return images;
    }
}
