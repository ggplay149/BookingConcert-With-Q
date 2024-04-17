package com.week4.concert.storage.queue.ongoing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OngoingJpaRepository extends JpaRepository<OngoingEntity, Long> {

    @Query("SELECT a FROM OngoingEntity a WHERE a.status = 'Ongoing' AND a.userId =:userId")
    Optional<OngoingEntity> check(@Param("userId") Long uesrId);


    @Query("SELECT COUNT(a) FROM OngoingEntity a WHERE a.status ='Ongoing'")
    Integer countOngoing();

    @Modifying
    @Query("UPDATE OngoingEntity a SET a.status = 'Done' WHERE a.id =:id")
    void updateDone(@Param("id") Long id);

}
