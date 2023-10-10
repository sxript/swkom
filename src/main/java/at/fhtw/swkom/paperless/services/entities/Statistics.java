package at.fhtw.swkom.paperless.services.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private long documentsTotalCount;
    private long documentsInboxCount;
    private long inboxTagCount;
    private long characterCount;

    // TODO documentFileTypeCounts
    //private List<DocumentFileTypeCount> documentFileTypeCounts;
}
