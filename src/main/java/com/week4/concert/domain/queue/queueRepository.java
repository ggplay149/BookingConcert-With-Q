package com.week4.concert.domain.queue;

public interface queueRepository {

    String checkUserStatus(Long userId);

    void insert(Long userId,String key);

    void remove(Long userId,String key);

    Integer countActive();

}
