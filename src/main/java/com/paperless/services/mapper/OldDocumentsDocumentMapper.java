package com.paperless.services.mapper;

import com.paperless.persistence.entities.*;
import com.paperless.persistence.repositories.AuthUserRepository;
import com.paperless.persistence.repositories.DocumentsDocumenttypeRepository;
import org.mapstruct.*;
import com.paperless.olddto.DocumentsDocumentDTO;
import com.paperless.persistence.repositories.DocumentsCorrespondentRepository;
import com.paperless.persistence.repositories.DocumentsStoragepathRepository;
import org.webjars.NotFoundException;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OldDocumentsDocumentMapper {
    @Mapping(target="owner", ignore = true)
    @Mapping(target="documentType", ignore = true)
    @Mapping(target="correspondent", ignore = true)
    @Mapping(target="storagePath", ignore = true)
    DocumentsDocumentDTO updateDocumentsDocumentDTO(DocumentsDocument documentsDocument, @MappingTarget DocumentsDocumentDTO documentsDocumentDTO);

    @Mapping(target="owner", ignore = true)
    @Mapping(target="documentType", ignore = true)
    @Mapping(target="correspondent", ignore = true)
    @Mapping(target="storagePath", ignore = true)
    @Mapping(target="id", ignore = true)
    DocumentsDocument updateDocumentsDocument(DocumentsDocumentDTO documentsDocumentDTO, @MappingTarget DocumentsDocument documentsDocument,
                                              @Context AuthUserRepository authUserRepository , @Context DocumentsDocumenttypeRepository documentsDocumenttypeRepository,
                                              @Context DocumentsCorrespondentRepository documentsCorrespondentRepository, @Context DocumentsStoragepathRepository documentsStoragepathRepository);



    @AfterMapping
    default void afterUpdateDocumentsDocumentDTO(DocumentsDocument documentsDocument, @MappingTarget DocumentsDocumentDTO documentsDocumentDTO) {
        documentsDocumentDTO.setOwner(documentsDocument.getOwner().getId());
        documentsDocumentDTO.setDocumentType(documentsDocument.getDocumentType().getId());
        documentsDocumentDTO.setCorrespondent(documentsDocument.getCorrespondent().getId());
        documentsDocumentDTO.setStoragePath(documentsDocument.getStoragePath().getId());
    }

    @AfterMapping
    default void afterUpdateDocumentsDocument(DocumentsDocumentDTO documentsDocumentDTO, @MappingTarget DocumentsDocument documentsDocument,
                                              @Context AuthUserRepository authUserRepository , @Context DocumentsDocumenttypeRepository documentsDocumenttypeRepository,
                                              @Context DocumentsCorrespondentRepository documentsCorrespondentRepository,
                                              @Context DocumentsStoragepathRepository documentsStoragepathRepository) {

        if (documentsDocumentDTO.getOwner() != null && (documentsDocument.getOwner() == null || !documentsDocument.getOwner().getId().equals(documentsDocumentDTO.getOwner()))) {
            final AuthUser owner = authUserRepository.findById(documentsDocumentDTO.getOwner())
                    .orElseThrow(() -> new NotFoundException("owner not found"));
            documentsDocument.setOwner(owner);
        }

        if (documentsDocumentDTO.getStoragePath() != null && (documentsDocument.getStoragePath() == null || !documentsDocument.getStoragePath().getId().equals(documentsDocumentDTO.getStoragePath()))) {
            final DocumentsStoragepath storagepath = documentsStoragepathRepository.findById(documentsDocumentDTO.getStoragePath())
                    .orElseThrow(() -> new NotFoundException("storagepath not found"));
            documentsDocument.setStoragePath(storagepath);
        }

        if (documentsDocumentDTO.getDocumentType() != null && (documentsDocument.getDocumentType() == null || !documentsDocument.getDocumentType().getId().equals(documentsDocumentDTO.getDocumentType()))) {
            final DocumentsDocumenttype documentsDocumenttype = documentsDocumenttypeRepository.findById(documentsDocumentDTO.getDocumentType())
                    .orElseThrow(() -> new NotFoundException("documentsDocumenttype not found"));
            documentsDocument.setDocumentType(documentsDocumenttype);
        }

        if (documentsDocumentDTO.getCorrespondent() != null && (documentsDocument.getCorrespondent() == null || !documentsDocument.getCorrespondent().getId().equals(documentsDocumentDTO.getCorrespondent()))) {
            final DocumentsCorrespondent documentsCorrespondent = documentsCorrespondentRepository.findById(documentsDocumentDTO.getCorrespondent())
                    .orElseThrow(() -> new NotFoundException("documentsCorrespondent not found"));
            documentsDocument.setCorrespondent(documentsCorrespondent);
        }
    }
}
