package com.ocr.paperlessOcr.service.impl;

import com.ocr.paperlessOcr.service.Impl.OCRServiceImpl;
import com.ocr.paperlessOcr.service.Impl.RabbitMQListenerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RabbitMQListenerServiceImplTest {


    @Mock
    private OCRServiceImpl ocrService;

    @InjectMocks
    private RabbitMQListenerServiceImpl rabbitMQListenerService;

    private static final String VALID_MESSAGE = "SampleMessage";


    @Test
    void processQueue_ValidMessage_CallsOCRService() {

        rabbitMQListenerService.processQueue(VALID_MESSAGE);


        verify(ocrService, times(1)).performOCR(VALID_MESSAGE);
    }
}
