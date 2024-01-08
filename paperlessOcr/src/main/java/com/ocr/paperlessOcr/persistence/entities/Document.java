package com.ocr.paperlessOcr.persistence.entities;



import java.time.OffsetDateTime;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
public class Document {

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

    @Column(nullable = false, length = 128)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private OffsetDateTime created;

    @Column(nullable = false)
    private OffsetDateTime modified;

    @Column(nullable = false, length = 32)
    private String checksum;

    @Column(nullable = false)
    private OffsetDateTime added;

    @Column(nullable = false, length = 11)
    private String storageType;

    @Column(length = 1024)
    private String filename;

    @Column
    private Integer archiveSerialNumber;

    @Column(nullable = false, length = 256)
    private String mimeType;

    @Column(length = 32)
    private String archiveChecksum;

    @Column(length = 1024)
    private String archiveFilename;

    @Column(length = 1024)
    private String originalFilename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "correspondent_id")
    private Correspondent correspondent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "storage_path_id")
    private StoragePath storagePath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToMany(mappedBy = "document")
    private Set<DocumentsNote> documentDocumentsNotes;

    @OneToMany(mappedBy = "document")
    private Set<DocumentTags> documentDocumentTags;



    public void setId(final Integer id) {
        this.id = id;
    }



    public void setTitle(final String title) {
        this.title = title;
    }



    public void setContent(final String content) {
        this.content = content;
    }



    public void setCreated(final OffsetDateTime created) {
        this.created = created;
    }



    public void setModified(final OffsetDateTime modified) {
        this.modified = modified;
    }



    public void setChecksum(final String checksum) {
        this.checksum = checksum;
    }



    public void setAdded(final OffsetDateTime added) {
        this.added = added;
    }



    public void setStorageType(final String storageType) {
        this.storageType = storageType;
    }



    public void setFilename(final String filename) {
        this.filename = filename;
    }



    public void setArchiveSerialNumber(final Integer archiveSerialNumber) {
        this.archiveSerialNumber = archiveSerialNumber;
    }



    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }



    public void setArchiveChecksum(final String archiveChecksum) {
        this.archiveChecksum = archiveChecksum;
    }



    public void setArchiveFilename(final String archiveFilename) {
        this.archiveFilename = archiveFilename;
    }



    public void setOriginalFilename(final String originalFilename) {
        this.originalFilename = originalFilename;
    }



    public void setCorrespondent(final Correspondent correspondent) {
        this.correspondent = correspondent;
    }



    public void setDocumentType(final DocumentType documentType) {
        this.documentType = documentType;
    }



    public void setStoragePath(final StoragePath storagePath) {
        this.storagePath = storagePath;
    }



    public void setOwner(final AuthUser owner) {
        this.owner = owner;
    }



    public void setDocumentDocumentsNotes(final Set<DocumentsNote> documentDocumentsNotes) {
        this.documentDocumentsNotes = documentDocumentsNotes;
    }

    public Set<DocumentTags> getDocumentDocumentsDocumentTagses() {
        return documentDocumentTags;
    }

    public void setDocumentDocumentsDocumentTagses(
            final Set<DocumentTags> documentDocumentTags) {
        this.documentDocumentTags = documentDocumentTags;
    }

}
