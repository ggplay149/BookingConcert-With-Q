/*
 * Copyright (c) 2022 ABC-MART. All rights reserved.
 *
 * This software is the confidential and proprietary information of ABC-MART.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance wih the terms of the license agreement you entered into
 * with ABC-MART.
 */
package com.week4.concert.infrastructure.queue.waiting;

import com.week4.concert.domain.queue.waiting.Waiting;
import com.week4.concert.domain.queue.waiting.WaitingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WaitingCoreRepository implements WaitingRepository {

    private final WaitingJpaRepository waitingJpaRepository;

    @Override
    public List<Waiting> selectTopN(int topN) {
        List<WaitingEntity> result = waitingJpaRepository.selectTopN(PageRequest.of(0, topN));
        List<Waiting> listToWaiting = new ArrayList<>();
        for (WaitingEntity entity : result) listToWaiting.add(entity.toWaiting());
        return listToWaiting;
    }

    @Override
    public Waiting check(Long userId) {
        return waitingJpaRepository.check(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저가 조회되지 않습니다."))
                .toWaiting();
    }

    @Override
    @Transactional
    public void save(Long userId) {
        waitingJpaRepository.save(WaitingEntity.builder()
                .userId(userId)
                .status("Waiting")
                .build());
    }

    @Override
    @Transactional
    public void updateDone(Long id) {
        waitingJpaRepository.updateDone(id);
    }
}
