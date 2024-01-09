package com.paperless.services.mapper;

import com.paperless.persistence.entities.*;
import com.paperless.persistence.repositories.*;
import com.paperless.services.dto.gets.GetDocument200Response;
import com.paperless.services.dto.gets.GetDocument200ResponsePermissions;
import com.paperless.services.dto.gets.GetDocuments200ResponseResultsInnerNotesInner;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class Get200Mapper implements EntityMapper<GetDocument200Response, Document>{

    private final DocumentsCorrespondentRepository correspondentRepository;
    private final DocumentsDocumenttypeRepository documentTypeRepository;
    private final DocumentsStoragepathRepository storagePathRepository;
    private final AuthUserRepository userRepository;
    private final DocumentsDocumentTagsRepository documentTagsRepository;

    private final PermissionsMapper permissionsMapper;
    private final DocumentNotesMapper documentNotesMapper;

    @Autowired
    protected Get200Mapper(DocumentsCorrespondentRepository correspondentRepository, DocumentsDocumenttypeRepository documentTypeRepository, DocumentsStoragepathRepository storagePathRepository, AuthUserRepository userRepository, DocumentsDocumentTagsRepository documentTagsRepository, PermissionsMapper PermissionsMapper, DocumentNotesMapper documentNotesMapper) {
        this.correspondentRepository = correspondentRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.storagePathRepository = storagePathRepository;
        this.userRepository = userRepository;
        this.documentTagsRepository = documentTagsRepository;
        this.permissionsMapper = PermissionsMapper;
        this.documentNotesMapper = documentNotesMapper;
    }

    @Mapping(target = "correspondent", source = "correspondent", qualifiedByName = "correspondentDto")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeDto")
    @Mapping(target = "storagePath", source = "storagePath", qualifiedByName = "storagePathDto")
    @Mapping(target = "documentDocumentsDocumentTagses", source = "tags", qualifiedByName = "tagsDto")
    @Mapping(target = "archiveSerialNumber", source = "archiveSerialNumber", qualifiedByName = "archiveSerialNumberDto")
    @Mapping(target = "owner", source = "owner", qualifiedByName = "ownerDto")
    @Mapping(target = "documentDocumentsNotes", source = "notes", qualifiedByName = "notesDto")
    @Mapping(target = "created", source = "createdDate", qualifiedByName = "createdDto")
    @Mapping(target = "modified", source = "modified", qualifiedByName = "modifyDto")
    @Mapping(target = "added", source = "added", qualifiedByName = "addedDto")
    public abstract Document toEntity(GetDocument200Response dto);

    @Mapping(target = "correspondent", source = "correspondent", qualifiedByName = "correspondentEntity")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeEntity")
    @Mapping(target = "storagePath", source = "storagePath", qualifiedByName = "storagePathEntity")
    @Mapping(target = "tags", source = "documentDocumentsDocumentTagses", qualifiedByName = "tagsEntity")
    @Mapping(target = "createdDate", source = "created", qualifiedByName = "createEntity")
    @Mapping(target = "created", source = "created", qualifiedByName = "createEntity")
    @Mapping(target = "owner", source = "owner", qualifiedByName = "ownerEntity")
    @Mapping(target = "permissions", source = "owner", qualifiedByName = "permissionsEntity")
    @Mapping(target = "notes", source = "documentDocumentsNotes", qualifiedByName = "notesEntity")
    @Mapping(target = "modified", source = "modified", qualifiedByName = "modifyEntity")
    @Mapping(target = "added", source = "added", qualifiedByName = "addedEntity")
    public abstract GetDocument200Response toDto(Document entity);

    @Named("correspondentEntity")
    Integer map(Correspondent correspondent) {
        if(correspondent == null) return null;

        return correspondent.getId();
    }

    @Named("documentTypeEntity")
    Integer map(DocumentType documentType) {
        if(documentType == null) return null;
        return documentType.getId();
    }

    @Named("storagePathEntity")
    Integer map(StoragePath storagePath) {
        if(storagePath == null) return null;
        return storagePath.getId();
    }

    @Named("ownerEntity")
    Integer map(AuthUser owner) {
        if(owner == null) return null;

        return owner.getId();
    }

    @Named("tagsEntity")
    List<Integer> map(Set<DocumentTags> tags) {
        if(tags == null) return Collections.emptyList();
        return tags.stream().map(DocumentTags::getId).toList();
    }

    @Named("permissionsEntity")
    GetDocument200ResponsePermissions mapPermissions(AuthUser owner) {
        if(owner == null) return null;
        return permissionsMapper.toDto(owner);
    }

    @Named("notesEntity")
    List<GetDocuments200ResponseResultsInnerNotesInner> mapNotes(Set<DocumentsNote> notes) {
        if(notes == null) return null;
        return notes.stream().map( note->documentNotesMapper.toDto(note) ).toList();
    }

    // map created to createdDate (Date without the time)
    @Named("createdToCreatedDate")
    OffsetDateTime mapCreatedDate(OffsetDateTime value) {
        return value!=null ? value.withOffsetSameInstant(ZoneOffset.UTC).toLocalDate().atStartOfDay().atOffset(ZoneOffset.UTC) : null;
    }

    @Named("correspondentDto")
    Correspondent mapCorrespondent(Integer value) {
        if(value == null) return null;
        return correspondentRepository.findById(value).orElse(null);
    }

    @Named("documentTypeDto")
    DocumentType mapDocumentType(Integer value) {
        if(value == null) return null;

        return documentTypeRepository.findById(value).orElse(null);
    }

    @Named("storagePathDto")
    StoragePath mapStoragePath(Integer value) {
        if(value == null) return null;
        return storagePathRepository.findById(value).orElse(null);
    }

    @Named("ownerDto")
    AuthUser mapOwner(Integer value) {
        if(value == null) return null;
        return userRepository.findById(value).orElse(null);
    }

    @Named("tagsDto")
    Set<DocumentTags> mapDocTag(List<Integer> value) {
        if(value == null) return null;
        return new HashSet<>(documentTagsRepository.findAllById(value));
    }

    @Named("archiveSerialNumberDto")
    Integer mapArchiveSerialNumber(String value) {
        if(value==null || value.isEmpty()) return null;
        return Integer.parseInt(value);
    }

    @Named("notesDto")
    Set<DocumentsNote> mapNotes(List<GetDocuments200ResponseResultsInnerNotesInner> value) {
        if(value==null || value.isEmpty()) return null;

        HashSet<DocumentsNote> notes = new HashSet<>();

        for(GetDocuments200ResponseResultsInnerNotesInner note : value) {
            notes.add(documentNotesMapper.toEntity(note));
        }

        return notes;
    }

    @Named("createdDto")
    OffsetDateTime mapCreated(String value) {
        return value!=null ? OffsetDateTime.parse(value) : null;
    }

    @Named("modifyDto")
    OffsetDateTime mapModifyDto(String value) {
        return OffsetDateTime.parse(value);
    }

    @Named("modifyEntity")
    String mapModify(OffsetDateTime value) {
        return value.toString();
    }

    @Named("addedDto")
    OffsetDateTime mapAdded(String value) {
        return OffsetDateTime.parse(value);
    }

    @Named("addedEntity")
    String mapAdded(OffsetDateTime value) {
        return value.toString();
    }

    @Named("createDto")
    OffsetDateTime mapCreatedDto(String value) {
        return OffsetDateTime.parse(value);
    }

    @Named("createEntity")
    String mapCreated(OffsetDateTime value) {
        return value.toString();
    }



}
