package com.paperless.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Correspondent {

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

    @OneToMany(mappedBy = "correspondent")
    private Set<Document> correspondentDocuments;

    @Setter
    @Getter
    @OneToMany(mappedBy = "assignCorrespondent")
    private Set<PaperlessMailMailrule> assignCorrespondentPaperlessMailMailrules;

    public Set<Document> getCorrespondentDocumentsDocuments() {
        return correspondentDocuments;
    }

    public void setCorrespondentDocumentsDocuments(
            final Set<Document> correspondentDocuments) {
        this.correspondentDocuments = correspondentDocuments;
    }

}
