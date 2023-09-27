package at.fhtw.swkom.paperless.services.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "new_correspondent")
public class NewDocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String match;

    @NotNull
    @Column(name = "matching_algorithm")
    private int matchingAlgortihm;
    @NotNull
    @Column(name = "is_insensitive")
    private boolean isSensitive;

}
