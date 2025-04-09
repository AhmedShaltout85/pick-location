package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.handasatTools.HandasatToolsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHandasatToolsRepository extends JpaRepository<HandasatToolsEntity, Long> {
List<HandasatToolsEntity> findByHandasahName(String handasahName);
}
