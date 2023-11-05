package com.paperless.olddto.dtos;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

/**
 * DTO for {@link com.paperless.persistence.entities.DocumentsDocument}
 */
@Value
public class DocumentsDocumentDto implements Serializable {
    @NotNull
    Integer id;
    @NotNull
    @Size(max = 128)
    String title;
    @NotNull
    String content;
    @NotNull
    OffsetDateTime created;
    @NotNull
    OffsetDateTime modified;
    @NotNull
    @Size(max = 32)
    String checksum;
    @NotNull
    OffsetDateTime added;
    @NotNull
    @Size(max = 11)
    String storageType;
    @Size(max = 1024)
    String filename;
    Integer archiveSerialNumber;
    @NotNull
    @Size(max = 256)
    String mimeType;
    @Size(max = 32)
    String archiveChecksum;
    @Size(max = 1024)
    String archiveFilename;
    @Size(max = 1024)
    String originalFilename;
    @NotNull
    Integer correspondentId;
    @NotNull
    Integer documentTypeId;
    @NotNull
    Integer storagePathId;
    @NotNull
    Integer ownerId;
    @NotNull
    Set<Integer> documentDocumentsNoteIds;
    @NotNull
    Set<Integer> documentDocumentsDocumentTagsIds;
}