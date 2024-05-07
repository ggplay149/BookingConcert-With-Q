package com.week4.concert.domain.queue;

public interface queueRepository {

    String checkUserStatus(Long userId);

    void insertWait(Long userId);

    void insertActive(Long userId);

    void removeWait(Long userId,String key);

    void removeActive(Long userId,String key);

    Long countActive();

}
