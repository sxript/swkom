package at.fhtw.swkom.paperless.services.persistance;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class DocTag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private int id;
    @Column
    private String slug;
    @Column
    private String name;
    @Column
    private String color;
    @Column
    private String match;
    @NotNull
    @Column(name = "matching_algorithm")
    private int matchingAlgorithm;
    @NotNull
    @Column(name = "is_insensitive")
    private boolean isInsensitive;
    @NotNull
    @Column(name = "is_inbox_tag")
    private boolean isInboxTag;
    @NotNull
    @Column(name = "document_count")
    private int documentCount;
}
