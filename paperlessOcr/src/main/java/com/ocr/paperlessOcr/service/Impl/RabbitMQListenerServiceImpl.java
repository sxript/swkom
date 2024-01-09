package com.ocr.paperlessOcr.service.Impl;

import com.ocr.paperlessOcr.service.Impl.OCRServiceImpl;
import com.ocr.paperlessOcr.service.RabbitMQListenerService;
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
        ocrService.performOCR(message);
    }
}
