package com.a08r.pick_location.repositores;



import com.a08r.pick_location.models.users.PickLocationUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPickLocationUsersRepository extends JpaRepository<PickLocationUsersEntity, Long> {

    Optional<PickLocationUsersEntity> findByUserName(String userName);

    Optional<PickLocationUsersEntity> findByPassword(String password);


    Optional<PickLocationUsersEntity> findByUserNameAndPassword(String userName, String password);

    List<PickLocationUsersEntity> findByRoleAndControlUnit(int role, String controlUnit);
}
