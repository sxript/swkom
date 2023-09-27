package at.fhtw.swkom.paperless.services.persistance;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Document {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private int id;

    @Column
    private int correspondent;

    @Column(name = "document_type")
    private int documentType;

    @Column(name = "storage_path")
    private int storagePath;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private int[] tags;

    @NotNull
    @Column
    private Timestamp created;

    @NotNull
    @Column(name = "created_date")
    private Timestamp createdDate;

    @NotNull
    @Column
    private Timestamp modified;

    @NotNull
    @Column
    private Timestamp added;

    @Column(name = "archive_serial_number")
    private String archiveSerialNumber;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "archived_file_name")
    private String archivedFileName;
}
