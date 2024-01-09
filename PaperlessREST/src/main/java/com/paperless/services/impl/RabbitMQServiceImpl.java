package com.paperless.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paperless.services.RabbitMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQServiceImpl implements RabbitMQService {
    private final RabbitTemplate rabbitTemplate;
    private final Queue ocrScanQueue;


    @Autowired
    public RabbitMQServiceImpl(RabbitTemplate rabbitTemplate, Queue ocrScanQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.ocrScanQueue = ocrScanQueue;
    }

    @Override
    public boolean saveInQueue(String queueMessage) {

        if(queueMessage==null || queueMessage.trim().isEmpty()){
            log.error("Queue message is empty");
            return false;
        }
        rabbitTemplate.convertAndSend(ocrScanQueue.getName(), queueMessage);
        return true;
    }

}
