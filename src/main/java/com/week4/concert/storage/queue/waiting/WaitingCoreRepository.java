/*
 * Copyright (c) 2022 ABC-MART. All rights reserved.
 *
 * This software is the confidential and proprietary information of ABC-MART.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance wih the terms of the license agreement you entered into
 * with ABC-MART.
 */
package com.week4.concert.storage.queue.waiting;

import com.week4.concert.domain.queue.ongoing.OngoingRepository;
import com.week4.concert.domain.queue.waiting.Waiting;
import com.week4.concert.domain.queue.waiting.WaitingRepository;
import com.week4.concert.storage.queue.ongoing.OngoingEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WaitingCoreRepository implements WaitingRepository {

    private final WaitingJpaRepository waitingJpaRepository;

    public WaitingCoreRepository(WaitingJpaRepository waitingJpaRepository) {
        this.waitingJpaRepository = waitingJpaRepository;
    }

    @Override
    public List<Waiting> selectTopN(int topN) {

        List<WaitingEntity> result = waitingJpaRepository.selectTopN(PageRequest.of(0, topN));
        List<Waiting> listToWaiting = new ArrayList<>();

        for(WaitingEntity entity : result) listToWaiting.add(entity.toWaiting());

        return listToWaiting;
    }

    @Override
    public void save(Long userId) {
        waitingJpaRepository.save(WaitingEntity.builder().userId(userId).build());
    }

    @Override
    public void deleteById(Long userId) {
        waitingJpaRepository.deleteById(userId);
    }


}
