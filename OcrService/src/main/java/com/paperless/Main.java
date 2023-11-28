package com.paperless;

import com.paperless.services.OcrService;
import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        OcrService ocrService = new OcrService();
        try {
            String s = ocrService.performOcr("./2023-11-28_17-04.png");
            System.out.println(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }
    }
}