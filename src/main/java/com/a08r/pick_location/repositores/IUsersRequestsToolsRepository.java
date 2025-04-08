package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.handasatTools.UsersRequestsToolsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersRequestsToolsRepository extends JpaRepository<UsersRequestsToolsEntity, Long> {
}
