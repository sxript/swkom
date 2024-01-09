package com.paperless.services;

public interface RabbitMQService {
    boolean saveInQueue(String queueMessage);
}
