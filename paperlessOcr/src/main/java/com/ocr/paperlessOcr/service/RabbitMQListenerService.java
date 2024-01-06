package com.ocr.paperlessOcr.service;

public interface RabbitMQListenerService {
    public void processQueue(String message);
}
