package com.paperless.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;


@Entity
public class Document {

    @Setter
    @Getter
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Setter
    @Getter
    @Column(nullable = false, length = 128)
    private String title;

    @Setter
    @Getter
    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Setter
    @Getter
    @Column(nullable = false)
    private OffsetDateTime created;

    @Setter
    @Getter
    @Column(nullable = false)
    private OffsetDateTime modified;

    @Setter
    @Getter
    @Column(nullable = false, length = 32)
    private String checksum;

    @Setter
    @Getter
    @Column(nullable = false)
    private OffsetDateTime added;

    @Setter
    @Getter
    @Column(nullable = false, length = 11)
    private String storageType;

    @Setter
    @Getter
    @Column(length = 1024)
    private String filename;

    @Setter
    @Getter
    @Column
    private Integer archiveSerialNumber;

    @Setter
    @Getter
    @Column(nullable = false, length = 256)
    private String mimeType;

    @Setter
    @Getter
    @Column(length = 32)
    private String archiveChecksum;

    @Setter
    @Getter
    @Column(length = 1024)
    private String archiveFilename;

    @Setter
    @Getter
    @Column(length = 1024)
    private String originalFilename;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "correspondent_id")
    private Correspondent correspondent;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY,cascade  = CascadeType.ALL)
    @JoinColumn(name = "storage_path_id")
    private StoragePath storagePath;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @Setter
    @Getter
    @OneToMany(mappedBy = "document")
    private Set<DocumentsNote> documentDocumentsNotes;

    @OneToMany(mappedBy = "document")
    private Set<DocumentTags> documentDocumentTags;

    public Set<DocumentTags> getDocumentDocumentsDocumentTagses() {
        return documentDocumentTags;
    }

    public void setDocumentDocumentsDocumentTagses(
            final Set<DocumentTags> documentDocumentTags) {
        this.documentDocumentTags = documentDocumentTags;
    }

}
