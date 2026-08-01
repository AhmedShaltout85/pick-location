package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.neighborhood.NeighborhoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface INeighborhoodRepository extends JpaRepository<NeighborhoodEntity, Long> {
    Optional<NeighborhoodEntity> findByNeighborhoodName(String neighborhoodName);
}
