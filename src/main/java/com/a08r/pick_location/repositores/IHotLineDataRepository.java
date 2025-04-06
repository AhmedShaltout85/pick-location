package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.hotline.HotLineDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotLineDataRepository extends JpaRepository<HotLineDataEntity, Long> {
}
