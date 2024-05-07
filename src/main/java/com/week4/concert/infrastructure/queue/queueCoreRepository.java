/*
 * Copyright (c) 2022 ABC-MART. All rights reserved.
 *
 * This software is the confidential and proprietary information of ABC-MART.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance wih the terms of the license agreement you entered into
 * with ABC-MART.
 */
package com.week4.concert.infrastructure.queue;

import com.week4.concert.domain.queue.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class queueCoreRepository implements QueueRepository {

    private final RedisTemplate<String,String> redisTemplate;

    @Override
    public Boolean checkUserStatus(Long userId) {
        return redisTemplate.opsForSet().isMember("Active", userId.toString());
    }

    @Override
    public void insertWait(Long userId) {
        Double score = (double) System.currentTimeMillis();
        redisTemplate.opsForZSet().add("Wait", userId.toString(), score);
    }

    @Override
    public void insertActive(Long userId) {
        redisTemplate.opsForSet().add("Active", userId.toString());
    }

    @Override
    public void removeWait(Long userId) {
        redisTemplate.opsForZSet().remove("Wait", userId.toString());
    }

    @Override
    public void removeActive(Long userId) {
        redisTemplate.opsForSet().remove("Active", userId.toString());
    }

    @Override
    public Long countActive() {
        return redisTemplate.opsForSet().size("Active");
    }

    @Override
    public void reset() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Override
    public String[] getTopNFromWait(Long topN) {
        Set<String> topValues = redisTemplate.opsForZSet().range("Wait", 0, topN - 1);
        return topValues.toArray(new String[(Integer)topN]);
    }
}
