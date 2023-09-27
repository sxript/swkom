package at.fhtw.swkom.paperless.services.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "new_correspondent")
public class NewCorrespondent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    private int id;
    private String name;
    private String match;
    @NotNull
    @Column(name = "matching_algorithm")
    private int matchingAlgortihm;
    @NotNull
    @Column(name = "is_insensitive")
    private boolean isSensitive;
    @NotNull
    @Column(name = "document_count")
    private int documentCount;
    @NotNull
    @Column(name = "last_correspondence")
    private String lastCorrespondence;


}
