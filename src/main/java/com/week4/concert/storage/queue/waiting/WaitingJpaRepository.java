package com.week4.concert.storage.queue.waiting;

import com.week4.concert.storage.queue.ongoing.OngoingEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WaitingJpaRepository extends JpaRepository<WaitingEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void save(Long userid);

    @Query("SELECT a FROM WaitingEntity a ORDER BY a.createdAt ASC")
    List<WaitingEntity> selectTopN(Pageable pageable);
}
