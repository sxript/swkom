package at.fhtw.swkom.paperless.services.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Correspondent {
    /*

id	integer($int64)
slug	string
nullable: true
name	string
nullable: true
match	string
nullable: true
matching_algorithm	integer($int64)
is_insensitive	boolean
document_count	integer($int64)
last_correspondence	string($date-time)

     */
    // ? identity
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private int id;

    private
}
