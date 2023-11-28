package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.DocumentsTag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsTagRepository extends JpaRepository<DocumentsTag, Integer> {
}
