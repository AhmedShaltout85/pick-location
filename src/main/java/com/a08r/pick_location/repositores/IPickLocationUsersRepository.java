package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.location.PickLocationEntity;
import com.a08r.pick_location.models.users.PickLocationUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPickLocationUsersRepository extends JpaRepository<PickLocationUsersEntity, Long> {

    //4-this way is run good with stored procedure(MUST DEFINE CORRECT RETURN TYPE)
//    @Procedure
//    List<Float> Lab_Parameters(int labCode, String testDate); // used with any return type


    //1-this way is run good with stored procedure(position args must added in the same arrange)
//    @Query(value = "EXEC Lab_Parameters ?1, ?2", nativeQuery = true) // used with any return type
//    List<Float> Lab_Parameters(@Param("Lab_Code") int labCode, @Param("Test_Date") String testDate);

    //1-this way is run good with stored procedure(named args)
//    @Query(value = "EXEC Lab_Parameters :Lab_Code, :Test_Date", nativeQuery = true) // used with any return type
//    List<Float> Lab_Parameters(@Param("Lab_Code") int labCode, @Param("Test_Date") String testDate);

    //2-this way is run good with stored procedure
//    @Procedure(name = "Lab_Parameters") // used with any return type
//    List<Float> Lab_Parameters(@Param("Lab_Code") int labCode, @Param("Test_Date") String testDate);

    //3-this way is run good with stored procedure
//    @Procedure(name = "Lab_Parameters") // used with any return type
//    List<Float> Lab_Parameters(@Param("Lab_Code") int labCode, @Param("Test_Date") String testDate);

//    @Procedure
//    List<PickLocationEntity> GetAddress();
//
//    @Procedure
//    void UpdateAddress(long id, String latitude, String longitude);

    //    List<PickLocationEntity> findByFlag(int flag);
//    Optional<PickLocationEntity> findByAddress(String address);
    Optional<PickLocationUsersEntity> findByUserName(String userName);

    Optional<PickLocationUsersEntity> findByPassword(String password);


    Optional<PickLocationUsersEntity> findByUserNameAndPassword(String userName, String password);
}
