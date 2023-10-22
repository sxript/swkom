package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.DocumentsCorrespondent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsCorrespondentRepository extends JpaRepository<DocumentsCorrespondent, Integer> {
}
