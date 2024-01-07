package com.ocr.paperlessOcr.persistence.repositories;

import com.ocr.paperlessOcr.persistence.entities.PaperlessMailProcessedmail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaperlessMailProcessedmailRepository extends JpaRepository<PaperlessMailProcessedmail, Integer> {
}
