package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.risk.RiskStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRiskStatusRepository extends JpaRepository<RiskStatusEntity, Long> {
    Optional<RiskStatusEntity> findByRiskName(String riskName);
}
