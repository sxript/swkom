package com.ocr.paperlessOcr.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ocr.paperlessOcr.persistence.entities.Document;
import com.ocr.paperlessOcr.persistence.repositories.DocumentsDocumentRepository;
import com.ocr.paperlessOcr.service.Impl.DocumentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class DocumentServiceImplTest {

    @Mock
    private DocumentsDocumentRepository documentRepository;

    @InjectMocks
    private DocumentServiceImpl documentService;

    private Document sampleDocument;

    @BeforeEach
    void setUp() {
        // Initialize sample entity for tests
        sampleDocument = new Document();
        sampleDocument.setId(1);
    }

    @Test
   public void saveDocument_ValidDocument_CallsRepositorySave() {

        documentService.saveDocument(sampleDocument);

        verify(documentRepository, times(1)).save(sampleDocument);
    }

    @Test
    void getById_ValidId_ReturnsOptionalDocument() {
        // Arrange
        when(documentRepository.findById(1)).thenReturn(Optional.of(sampleDocument));

        // Act
        Optional<Document> result = documentService.getById("1");

        // Assert
        assertTrue(result.isPresent());
        assertEquals(sampleDocument, result.get());
    }

    @Test
    void getById_InvalidId_ReturnsEmptyOptional() {
        // Arrange
        when(documentRepository.findById(2)).thenReturn(Optional.empty());

        // Act
        Optional<Document> result = documentService.getById("2");

        // Assert
        assertTrue(result.isEmpty());
    }
}
