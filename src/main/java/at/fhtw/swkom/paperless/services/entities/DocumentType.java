package at.fhtw.swkom.paperless.services.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentType {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private int id;

 private String name;
 private long matchingAlgorithm;
 private boolean isInsensitive;

 @OneToMany(mappedBy = "documentType")
 private List<Document> documents;
}
