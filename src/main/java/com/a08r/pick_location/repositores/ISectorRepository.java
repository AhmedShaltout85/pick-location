package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.sector.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISectorRepository extends JpaRepository<SectorEntity, Long> {
    Optional<SectorEntity> findBySectorName(String sectorName);
}
