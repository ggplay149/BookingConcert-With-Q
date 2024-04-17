package com.week4.concert.storage.queue.ongoing;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OngoingJpaRepository extends JpaRepository<OngoingEntity, Long> {

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM OngoingEntity a WHERE a.status = 'Ongoing' AND a.userId =:userId")
    Optional<OngoingEntity> check(@Param("userId") Long uesrId);


    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM OngoingEntity a WHERE a.status ='Ongoing'")
    Optional<List<OngoingEntity>> countOngoing();

    @Transactional
    @Modifying
    @Query("UPDATE OngoingEntity a SET a.status = 'Done' WHERE a.id =:id")
    void updateDone(@Param("id") Long id);

}
