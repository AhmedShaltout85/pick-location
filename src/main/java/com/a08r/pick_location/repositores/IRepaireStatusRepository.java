package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.repair.RepaireStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepaireStatusRepository extends JpaRepository<RepaireStatusEntity, Long> {
    Optional<RepaireStatusEntity> findByRepaireType(String repaireType);
}
