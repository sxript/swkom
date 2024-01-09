package com.paperless.services.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

import com.paperless.persistence.entities.Correspondent;
import com.paperless.persistence.entities.Document;
import com.paperless.persistence.entities.DocumentType;
import com.paperless.persistence.repositories.*;
import com.paperless.services.dto.DocumentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.jackson.nullable.JsonNullable;

@ExtendWith(MockitoExtension.class)
class DocumentMapperTest {

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

    @InjectMocks
    private DocumentMapper documentMapper = Mappers.getMapper(DocumentMapper.class);

    @Test
    void testToEntity() {
        // Create a sample DTO
        DocumentDTO documentDTO = DocumentDTO.builder()
                .correspondent(JsonNullable.of(1))
                .id(1)
                .archivedFileName(JsonNullable.of("archivedFileName"))
                .createdDate(JsonNullable.of(OffsetDateTime.of(2021, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC)).get())
                .documentType(JsonNullable.of(1))
                .build();

        // Mock the repository call
        Correspondent mockCorrespondent = new Correspondent();
        DocumentType mockDocumentType = new DocumentType();

        when(correspondentRepository.findById(1)).thenReturn(Optional.of(mockCorrespondent));
        when(documentTypeRepository.findById(1)).thenReturn(Optional.of(mockDocumentType));

        // Perform the mapping
        Document document = documentMapper.toEntity(documentDTO);

        // Assertions
        assertNotNull(document);
        assertEquals(mockCorrespondent, document.getCorrespondent());
        assertEquals(mockDocumentType, document.getDocumentType());

    }

    @Test
    void testToDto() {
        // Create a sample entity
        Document document = new Document();
        Correspondent correspondent = new Correspondent();
        document.setCorrespondent(correspondent);


        DocumentDTO documentDTO = documentMapper.toDto(document);


        assertNotNull(documentDTO);
        assertEquals(JsonNullable.of(correspondent.getId()), documentDTO.getCorrespondent());
    }
}
