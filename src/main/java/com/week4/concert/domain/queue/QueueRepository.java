package com.week4.concert.domain.queue;

public interface QueueRepository {

    Boolean checkUserStatus(Long userId);

    void insertWait(Long userId);

    void insertActive(Long userId);

    void removeWait(Long userId);

    void removeActive(Long userId);

    Long countActive();

    void reset();

    String[] getTopNFromWait(Long topN);

}
