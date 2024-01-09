package com.paperless.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
public class DocumentType {

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
    private String name;

    @Setter
    @Getter
    @Column(nullable = false, length = 256)
    private String match;

    @Setter
    @Getter
    @Column(nullable = false)
    private Integer matchingAlgorithm;

    @Setter
    @Getter
    @Column(nullable = false)
    private Boolean isInsensitive;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToMany(mappedBy = "documentType")
    private Set<Document> documentTypeDocuments;

    @Setter
    @Getter
    @OneToMany(mappedBy = "assignDocumentType")
    private Set<PaperlessMailMailrule> assignDocumentTypePaperlessMailMailrules;

    public Set<Document> getDocumentTypeDocumentsDocuments() {
        return documentTypeDocuments;
    }

    public void setDocumentTypeDocumentsDocuments(
            final Set<Document> documentTypeDocuments) {
        this.documentTypeDocuments = documentTypeDocuments;
    }

}
