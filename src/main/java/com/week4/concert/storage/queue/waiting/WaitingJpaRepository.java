package com.week4.concert.storage.queue.waiting;

import com.week4.concert.storage.queue.ongoing.OngoingEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface WaitingJpaRepository extends JpaRepository<WaitingEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void save(Long userid);
}
