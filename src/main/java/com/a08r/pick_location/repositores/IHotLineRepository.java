package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.hotline.HotLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotLineRepository extends JpaRepository<HotLineEntity, Long> {
}
