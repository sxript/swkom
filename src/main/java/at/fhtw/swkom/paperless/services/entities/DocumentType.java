package at.fhtw.swkom.paperless.services.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DocumentType {
   /*
   id	integer($int64)
slug	string nullable: true
name	string nullable: true
match	string nullable: true
matching_algorithm	integer($int64)
is_insensitive	boolean
document_count	integer($int64)
    */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private int id;

    @Column
    private String slug;

    @Column
    private String name;

    @Column
    private String match;

    @NotNull
    @Column(name = "matching_algorithm")
    private int matchingAlgorithm;

    @NotNull
    @Column(name = "is_insensitive")
    private boolean isInsensitive;

    @NotNull
    @Column(name = "document_count")
    private int documentCount;
}
