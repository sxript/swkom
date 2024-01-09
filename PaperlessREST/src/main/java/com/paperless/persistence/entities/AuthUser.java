package com.paperless.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;


@Entity
public class AuthUser {

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
    private String password;

    @Setter
    @Getter
    @Column
    private OffsetDateTime lastLogin;

    @Setter
    @Getter
    @Column(nullable = false)
    private Boolean isSuperuser;

    @Setter
    @Getter
    @Column(nullable = false, length = 150)
    private String username;

    @Setter
    @Getter
    @Column(nullable = false, length = 150)
    private String firstName;

    @Setter
    @Getter
    @Column(nullable = false, length = 150)
    private String lastName;

    @Setter
    @Getter
    @Column(nullable = false, length = 254)
    private String email;

    @Setter
    @Getter
    @Column(nullable = false)
    private Boolean isStaff;

    @Setter
    @Getter
    @Column(nullable = false)
    private Boolean isActive;

    @Setter
    @Getter
    @Column(nullable = false)
    private OffsetDateTime dateJoined;

    @Setter
    @Getter
    @OneToMany(mappedBy = "user")
    private Set<AuthUserGroups> userAuthUserGroupses;

    @OneToMany(mappedBy = "owner")
    private Set<Correspondent> ownerCorrespondents;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentType> ownerDocumentTypes;

    @OneToMany(mappedBy = "owner")
    private Set<StoragePath> ownerStoragePaths;

    @Setter
    @Getter
    @OneToMany(mappedBy = "owner")
    private Set<DocumentsTag> ownerDocumentsTags;

    @Setter
    @Getter
    @OneToMany(mappedBy = "user")
    private Set<DocumentsUisettings> userDocumentsUisettingses;

    @Setter
    @Getter
    @OneToMany(mappedBy = "owner")
    private Set<DocumentsSavedview> ownerDocumentsSavedviews;

    @Setter
    @Getter
    @OneToMany(mappedBy = "owner")
    private Set<PaperlessMailMailaccount> ownerPaperlessMailMailaccounts;

    @OneToMany(mappedBy = "owner")
    private Set<Document> ownerDocuments;

    @Setter
    @Getter
    @OneToMany(mappedBy = "user")
    private Set<DocumentsNote> userDocumentsNotes;

    @Setter
    @Getter
    @OneToMany(mappedBy = "owner")
    private Set<PaperlessMailMailrule> ownerPaperlessMailMailrules;

    @Setter
    @Getter
    @OneToMany(mappedBy = "user")
    private Set<AuthUserUserPermissions> userAuthUserUserPermissionses;

    @Setter
    @Getter
    @OneToMany(mappedBy = "user")
    private Set<AuthtokenToken> userAuthtokenTokens;

    @Setter
    @Getter
    @OneToMany(mappedBy = "owner")
    private Set<PaperlessMailProcessedmail> ownerPaperlessMailProcessedmails;

    public Set<Correspondent> getOwnerDocumentsCorrespondents() {
        return ownerCorrespondents;
    }

    public void setOwnerDocumentsCorrespondents(
            final Set<Correspondent> ownerCorrespondents) {
        this.ownerCorrespondents = ownerCorrespondents;
    }

    public Set<DocumentType> getOwnerDocumentsDocumenttypes() {
        return ownerDocumentTypes;
    }

    public void setOwnerDocumentsDocumenttypes(
            final Set<DocumentType> ownerDocumentTypes) {
        this.ownerDocumentTypes = ownerDocumentTypes;
    }

    public Set<StoragePath> getOwnerDocumentsStoragepaths() {
        return ownerStoragePaths;
    }

    public void setOwnerDocumentsStoragepaths(
            final Set<StoragePath> ownerStoragePaths) {
        this.ownerStoragePaths = ownerStoragePaths;
    }

    public Set<Document> getOwnerDocumentsDocuments() {
        return ownerDocuments;
    }

    public void setOwnerDocumentsDocuments(final Set<Document> ownerDocuments) {
        this.ownerDocuments = ownerDocuments;
    }

}
