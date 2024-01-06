package com.ocr.paperlessOcr.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListenerServiceImpl implements RabbitMQListenerService {

    private final OCRServiceImpl ocrService;

    @Autowired
    public RabbitMQListenerServiceImpl(OCRServiceImpl ocrService) {
        this.ocrService = ocrService;
    }

    @RabbitListener(queues = "ocrScanQueue")
    @Override
    public void processQueue(String message) {
        System.out.println(message);
        ocrService.performOCR(message);
    }
}
