package com.week4.concert.domain.queue.waiting;
public interface WaitingRepository {

    void save(Long userId);

    void deleteById(Long userId);
}
