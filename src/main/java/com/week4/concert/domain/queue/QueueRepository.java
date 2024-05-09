package com.week4.concert.domain.queue;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

public interface QueueRepository {

    Boolean checkUserStatus(Long userId, String key);

    void insert(Long userId , String key);

    void remove(Long userId , String key);

    Long countActiveUsers();

    void reset();

    String[] getNewActiveUsers(Long topN);

    Set<ZSetOperations.TypedTuple<String>> getUserExpiryTime();



}
