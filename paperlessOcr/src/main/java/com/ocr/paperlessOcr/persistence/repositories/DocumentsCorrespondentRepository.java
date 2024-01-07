package com.ocr.paperlessOcr.persistence.repositories;

import com.ocr.paperlessOcr.persistence.entities.Correspondent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
@Repository
public interface DocumentsCorrespondentRepository extends JpaRepository<Correspondent, Integer> {
}
