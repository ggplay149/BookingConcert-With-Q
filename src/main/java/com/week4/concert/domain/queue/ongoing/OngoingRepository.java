package com.week4.concert.domain.queue.ongoing;

public interface OngoingRepository {

    Ongoing check (Long uesrId);

    Integer countOngoing();

    void save(Long userId);

    void updateDone(Long userId);



}
