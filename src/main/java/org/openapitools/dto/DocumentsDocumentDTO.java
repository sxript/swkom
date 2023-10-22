package org.openapitools.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
public class DocumentsDocumentDTO {
    private Integer id;

    @NotNull
    @Size(max = 128)
    private String title;

    @NotNull
    private String content;

    @NotNull
    private OffsetDateTime created;

    @NotNull
    private OffsetDateTime modified;
    @NotNull
    @Size(max = 32)
    private String checksum;
    @NotNull
    private OffsetDateTime added;

    @NotNull
    @Size(max = 11)
    private String storageType;
    @Size(max = 1024)
    private String filename;
    private Integer archiveSerialNumber;
    @NotNull
    @Size(max = 256)
    private String mimeType;
    @Size(max = 32)
    private String archiveChecksum;
    @Size(max = 1024)
    private String archiveFilename;
    @Size(max = 1024)
    private String originalFilename;
    @NotNull
    private Integer correspondent;

    @NotNull
    private Integer documentType;

    @NotNull
    private Integer storagePath;

    @NotNull
    private Integer owner;

    @NotNull
    private Set<Integer> documentDocumentsNotes;

    @NotNull
    private Set<Integer> documentDocumentsTags;

}
