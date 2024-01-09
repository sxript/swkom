package com.paperless.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Setter
@Getter
@Entity
public class AuthPermission {

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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 100)
    private String contentType;

    @Column(nullable = false, length = 100)
    private String codename;

    @OneToMany(mappedBy = "permission")
    private Set<AuthGroupPermissions> permissionAuthGroupPermissionses;

    @OneToMany(mappedBy = "permission")
    private Set<AuthUserUserPermissions> permissionAuthUserUserPermissionses;

}
