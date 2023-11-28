package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.DocumentsNote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<DocumentsNote, Integer> {
}
