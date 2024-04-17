package com.week4.concert.storage.queue.waiting;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WaitingJpaRepository extends JpaRepository<WaitingEntity, Long> {

    @Query("SELECT a FROM WaitingEntity a WHERE a.status = 'Waiting' AND a.userId =:userId")
    Optional<WaitingEntity> check(@Param("userId") Long uesrId);


    @Query("SELECT a FROM WaitingEntity a WHERE a.status = 'Waiting' ORDER BY a.createdAt ASC")
    List<WaitingEntity> selectTopN(Pageable pageable);

    @Modifying
    @Query("UPDATE WaitingEntity a SET a.status = 'Done' WHERE a.id =:id")
    void updateDone(@Param("id") Long id);
}
