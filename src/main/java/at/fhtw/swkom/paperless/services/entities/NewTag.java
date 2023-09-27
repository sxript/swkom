package at.fhtw.swkom.paperless.services.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class NewTag {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "native")
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

}
