package com.paperless.services.mapper;

import com.paperless.persistence.entities.AuthUser;
import com.paperless.persistence.entities.Document;
import com.paperless.persistence.entities.DocumentsNote;
import com.paperless.persistence.repositories.*;
import com.paperless.services.dto.gets.GetDocuments200ResponseResultsInnerNotesInner;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class DocumentNotesMapper implements EntityMapper<GetDocuments200ResponseResultsInnerNotesInner, DocumentsNote> {
    @Autowired
    private DocumentsCorrespondentRepository correspondentRepository;
    @Autowired
    private DocumentsDocumenttypeRepository documentTypeRepository;
    @Autowired
    private DocumentsStoragepathRepository storagePathRepository;
    @Autowired
    private AuthUserRepository userRepository;
    @Autowired
    private DocumentsDocumentTagsRepository documentTagsRepository;
    @Autowired
    private DocumentsDocumentRepository documentRepository;

    @Mapping(target = "document", source = "document", qualifiedByName = "documentDto")
    @Mapping(target = "user", source = "user", qualifiedByName = "userDto")
    @Mapping(target = "created", source = "created", qualifiedByName = "createdDto")
    abstract public DocumentsNote toEntity(GetDocuments200ResponseResultsInnerNotesInner dto);

    @Mapping(target = "document", source = "document", qualifiedByName = "documentEntity")
    @Mapping(target = "user", source = "user", qualifiedByName = "userEntity")
    @Mapping(target = "created", source = "created", qualifiedByName = "createdEntity")
    abstract public GetDocuments200ResponseResultsInnerNotesInner toDto(DocumentsNote entity);

    @Named("userEntity")
    Integer map(AuthUser user) {
        return user.getId();
    }

    @Named("documentEntity")
    Integer map(Document document) {
        return document.getId();
    }

    @Named("createdEntity")
    String map(OffsetDateTime created) {
        return created.toString();
    }

    @Named("createdDto")
    OffsetDateTime map(String value) {
        return OffsetDateTime.parse(value);
    }
    @Named("userDto")
    AuthUser mapCorrespondent(Integer value) {
        return userRepository.findById(value).orElse(null);
    }

    @Named("documentDto")
    Document mapDocument(Integer value) {
        return documentRepository.findById(value).orElse(null);
    }

}
