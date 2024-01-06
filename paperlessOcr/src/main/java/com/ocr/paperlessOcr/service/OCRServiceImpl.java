package com.ocr.paperlessOcr.service;

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

@Service
public class OCRServiceImpl implements OCRService {
    private final MinioClient minioClient;

    @Autowired
    public OCRServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void performOCR(String pdfFileName) {

        System.out.println("in performing");

        var data = getPdfData(pdfFileName);


        try {
            var image = convertPdfToImages(data);
            System.out.println(image);
            System.out.println("----------------");

//            ITesseract tesseract = new Tesseract();
//
//
//            String result = tesseract.doOCR(image);
//            System.out.println(result);


        } catch (Exception ex) {
            System.out.println(ex);

        }


    }


    private byte[] getPdfData(String pdfFilePath) {


        String[] bucketAndFileName = extractBucketAndFileName(pdfFilePath);
        if (bucketAndFileName == null) return null;

//        log.info(bucketAndFileName[0] +", "+ bucketAndFileName[1]);

        String bucketName = bucketAndFileName[0];
        String fileName = bucketAndFileName[1];

        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build())) {

            return stream.readAllBytes();
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
//            log.error(e.getMessage(), e);
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
