package com.paperless.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQService {
    private final RabbitTemplate rabbitTemplate;
    private final Queue ocrScanQueue;
    private final ObjectMapper objectMapper;

    @Autowired
    public RabbitMQService(RabbitTemplate rabbitTemplate, Queue ocrScanQueue, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.ocrScanQueue = ocrScanQueue;
        this.objectMapper = objectMapper;
    }

    public void saveInQueue(String queueMessage) {
        rabbitTemplate.convertAndSend(ocrScanQueue.getName(), queueMessage);
        log.info("Document uploaded and sent to RabbitMQ for OCR processing.");
    }

}
