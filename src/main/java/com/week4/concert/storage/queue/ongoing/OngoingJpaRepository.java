package com.week4.concert.storage.queue.ongoing;

import com.week4.concert.storage.queue.ongoing.OngoingEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface OngoingJpaRepository extends JpaRepository<OngoingEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void save(Long userid);
}
