package com.paperless.services.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.OffsetDateTime;

import com.paperless.persistence.entities.AuthUser;
import com.paperless.persistence.entities.Document;
import com.paperless.persistence.entities.DocumentsNote;
import com.paperless.persistence.repositories.*;
import com.paperless.services.dto.gets.GetDocuments200ResponseResultsInnerNotesInner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DocumentNotesMapperTest {

    @Mock
    private DocumentsCorrespondentRepository correspondentRepository;

    @Mock
    private DocumentsDocumenttypeRepository documentTypeRepository;

    @Mock
    private DocumentsStoragepathRepository storagePathRepository;

    @Mock
    private AuthUserRepository userRepository;

    @Mock
    private DocumentsDocumentTagsRepository documentTagsRepository;

    @Mock
    private DocumentsDocumentRepository documentRepository;

    @InjectMocks
    private final DocumentNotesMapper documentNotesMapper = Mappers.getMapper(DocumentNotesMapper.class);

    private GetDocuments200ResponseResultsInnerNotesInner sampleNoteDto;
    private DocumentsNote sampleNoteEntity;

    @BeforeEach
    void setUp() {
        // Initialize sample DTO and entity for tests
        sampleNoteDto = new GetDocuments200ResponseResultsInnerNotesInner();
        sampleNoteDto.setDocument(1);
        sampleNoteDto.setUser(2);
        sampleNoteDto.setCreated("2022-01-01T12:00Z");

        sampleNoteEntity = new DocumentsNote();
        sampleNoteEntity.setDocument(new Document());
        sampleNoteEntity.setUser(new AuthUser());
        sampleNoteEntity.setCreated(OffsetDateTime.parse("2022-01-01T12:00Z"));
    }

    @Test
    void toEntity_ValidDTO_ReturnsEntityWithMappedValues() {
        // Arrange
        when(userRepository.findById(2)).thenReturn(Optional.of(new AuthUser()));
        when(documentRepository.findById(1)).thenReturn(Optional.of(new Document()));

        // Act
        DocumentsNote result = documentNotesMapper.toEntity(sampleNoteDto);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getDocument());
        assertNotNull(result.getUser());
        assertNotNull(result.getCreated());
    }

    @Test
    void toDto_ValidEntity_ReturnsDTOWithMappedValues() {


        GetDocuments200ResponseResultsInnerNotesInner result = documentNotesMapper.toDto(sampleNoteEntity);


        assertNotNull(result);
        assertEquals("2022-01-01T12:00Z", result.getCreated());
    }



}
