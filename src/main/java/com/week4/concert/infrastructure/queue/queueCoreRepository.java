/*
 * Copyright (c) 2022 ABC-MART. All rights reserved.
 *
 * This software is the confidential and proprietary information of ABC-MART.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance wih the terms of the license agreement you entered into
 * with ABC-MART.
 */
package com.week4.concert.infrastructure.queue;

import com.week4.concert.domain.queue.queueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class queueCoreRepository implements queueRepository {

    private final RedisTemplate<String,String> redisTemplate;


    @Override
    public String checkUserStatus(Long userId) {
        return null;
    }

    @Override
    public void insert(Long userId, String key) {

    }

    @Override
    public void remove(Long userId, String key) {

    }

    @Override
    public Integer countActive() {
        return null;
    }
}
