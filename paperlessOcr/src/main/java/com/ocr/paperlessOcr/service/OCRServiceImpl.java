package com.ocr.paperlessOcr.service;

import com.ocr.paperlessOcr.persistence.entities.Document;
import com.ocr.paperlessOcr.persistence.entities.StoragePath;
import com.ocr.paperlessOcr.persistence.repositories.DocumentsDocumentRepository;
import com.ocr.paperlessOcr.persistence.repositories.DocumentsStoragepathRepository;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OCRServiceImpl implements OCRService {
    private final MinioClient minioClient;

    private final DocumentsDocumentRepository documentsDocumentRepository;

    @Autowired
    public OCRServiceImpl(MinioClient minioClient,DocumentsDocumentRepository documentsDocumentRepository) {
        this.minioClient = minioClient;
        this.documentsDocumentRepository = documentsDocumentRepository;
    }

    @Override
    public void performOCR(String id) {

        System.out.println("in performing");

        var data = getPdfData(id);


        try {
            var image = convertPdfToImages(data);
            System.out.println(image);
            System.out.println("----------------");

            ITesseract tesseract = new Tesseract();
            String result = tesseract.doOCR(image);
            System.out.println(result);

            Optional<Document> document = documentsDocumentRepository.findById(Integer.parseInt(id));

            document.get().setContent(result);

            //ToDo: check the Optional
            documentsDocumentRepository.save(document.get());


        } catch (Exception ex) {
            System.out.println(ex);

        }
    }


    private byte[] getPdfData(String storageId) {



        Optional<Document> document = documentsDocumentRepository.findById(Integer.parseInt(storageId));

        System.out.println(document.get().getStoragePath().getPath() + "  Id: ");


        String[] bucketAndFileName = extractBucketAndFileName(document.get().getStoragePath().getPath());

        System.out.println("line 79");
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
