package com.paperless.persistence.repositories;

import com.paperless.persistence.entities.StoragePath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface DocumentsStoragepathRepository extends JpaRepository<StoragePath, Integer> {
}
