package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.location.TrackingLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITracingLocationRepository extends JpaRepository<TrackingLocationEntity, Long> {
    Optional<TrackingLocationEntity> findByAddress(String address);
    Optional<TrackingLocationEntity> findByAddressAndTechnicalName(String address, String technicalName);

}
