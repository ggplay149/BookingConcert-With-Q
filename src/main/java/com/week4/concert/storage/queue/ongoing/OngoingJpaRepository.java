package com.week4.concert.storage.queue.ongoing;

import com.week4.concert.domain.queue.ongoing.Ongoing;
import com.week4.concert.storage.queue.ongoing.OngoingEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OngoingJpaRepository extends JpaRepository<OngoingEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void save(Long userid);

    Optional<OngoingEntity> findByUserId(Long uesrId);

    @Query("SELECT COUNT(a) FROM OngoingEntity a")
    Integer countOngoing();
}
