package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.handasatTools.UsersRequestsToolsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsersRequestsToolsRepository extends JpaRepository<UsersRequestsToolsEntity, Long> {
    Optional<UsersRequestsToolsEntity> findByHandasahName(String handasahName);
    Optional<UsersRequestsToolsEntity> findByAddressAndToolName(String address, String toolName);
    Optional<UsersRequestsToolsEntity> findByHandasahNameAndRequestStatus(String handasahName, int requestStatus);
    List<UsersRequestsToolsEntity> findByHandasahNameAndRequestStatusAndAddress(String handasahName, int requestStatus, String address);
    List<UsersRequestsToolsEntity> findByHandasahNameAndTechNameAndAddress(String handasahName, String  techName, String address);
}
