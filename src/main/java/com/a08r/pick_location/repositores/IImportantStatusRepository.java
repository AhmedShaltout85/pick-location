package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.important.ImportantStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IImportantStatusRepository extends JpaRepository<ImportantStatusEntity, Long> {
    Optional<ImportantStatusEntity> findByImportantName(String importantName);
}
