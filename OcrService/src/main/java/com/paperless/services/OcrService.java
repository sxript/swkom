package com.paperless.services;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class OcrService {
    public String performOcr(String filePath) throws IOException, TesseractException {
        System.out.println("Performing OCR on " + filePath);
        if (filePath.endsWith(".pdf")) {
            return extractTextFromPdf(filePath);
        }

        return performOcrOnText(filePath);
    }

    private String extractTextFromPdf(String filePath) throws IOException {
        PDDocument document = Loader.loadPDF(new File(filePath));
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String pdfText = pdfTextStripper.getText(document);
        document.close();
        return pdfText;
    }

    private String performOcrOnText(String filePath) throws TesseractException {
        ITesseract tesseract = new Tesseract();
        return tesseract.doOCR(new File(filePath));
    }
}
