package com.ocr.paperlessOcr.service;

public interface RabbitMQListenerService {
     void processQueue(String message);
}
