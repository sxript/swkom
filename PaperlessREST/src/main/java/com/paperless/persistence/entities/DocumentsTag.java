package com.paperless.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
public class DocumentsTag {

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
    @Column(nullable = false)
    private Boolean isInboxTag;

    @Setter
    @Getter
    @Column(nullable = false, length = 7)
    private String color;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToMany(mappedBy = "tag")
    private Set<DocumentTags> tagDocumentTags;

    @Setter
    @Getter
    @OneToMany(mappedBy = "tag")
    private Set<PaperlessMailMailruleAssignTags> tagPaperlessMailMailruleAssignTagses;

    public Set<DocumentTags> getTagDocumentsDocumentTagses() {
        return tagDocumentTags;
    }

    public void setTagDocumentsDocumentTagses(
            final Set<DocumentTags> tagDocumentTags) {
        this.tagDocumentTags = tagDocumentTags;
    }

}
