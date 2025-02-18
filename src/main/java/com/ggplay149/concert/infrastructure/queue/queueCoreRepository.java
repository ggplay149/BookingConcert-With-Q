/*
 * Copyright (c) 2022 ABC-MART. All rights reserved.
 *
 * This software is the confidential and proprietary information of ABC-MART.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance wih the terms of the license agreement you entered into
 * with ABC-MART.
 */
package com.ggplay149.concert.infrastructure.queue;

import com.ggplay149.concert.domain.queue.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class queueCoreRepository implements QueueRepository {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Boolean checkUserStatus(Long userId, String key) {
        if(redisTemplate.opsForZSet().score(key,userId.toString()) == null) return false;
        return true;
    }

    @Override
    public void insert(Long userId, String key) {
        Double score = (double) System.currentTimeMillis()/ 1000.0;
        redisTemplate.opsForZSet().add(key, userId.toString(), score);
    }

    @Override
    public void remove(Long userId, String key) {
        redisTemplate.opsForZSet().remove(key, userId.toString());
    }

    @Override
    public Long countActiveUsers() {
        return redisTemplate.opsForZSet().size("Active");
    }

    @Override
    public void reset() {
        Set<String> keys = redisTemplate.keys("*");
        if (keys != null) redisTemplate.delete(keys);
    }

    @Override
    public String[] getNewActiveUsers(Long topN) {
        Set<String> topValues = redisTemplate.opsForZSet().range("Wait", 0, topN - 1);
        return topValues.toArray(new String[0]);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> getUserExpiryTime() {
        return redisTemplate.opsForZSet().rangeWithScores("Active", 0, -1);
    }

}
