package com.a08r.pick_location.repositores;

import com.a08r.pick_location.models.location.PickLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPickLocationRepository extends JpaRepository<PickLocationEntity, Long> {

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

    List<PickLocationEntity> findByFlag(int flag);
    List<PickLocationEntity> findByFlagAndIsFinished(int flag, int is_finished); // List<PickLocationEntity> findByFlag(int flag);
    Optional<PickLocationEntity> findByAddress(String address);
    Optional<PickLocationEntity> findByAddressAndFlag(String address, int flag);
    Optional<PickLocationEntity> findByAddressAndHandasahName(String address, String handasahName);
    Optional<PickLocationEntity> findByHandasahNameAndTechnicalNameAndIsFinished(String handasahName, String technicalName, int isFinished);
    List<PickLocationEntity> findByHandasahNameAndTechnicalName(String handasahName, String technicalName);
    List<PickLocationEntity> findByHandasahNameAndIsFinished(String handasahName, int isFinished);
    Optional<PickLocationEntity> findByAddressAndTechnicalNameAndIsApproved(String address, String technicalName, int isApproved);


}
