package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.DocumentsNote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsNoteRepository extends JpaRepository<DocumentsNote, Integer> {
}
