/*
 * Copyright (c) 2022 ABC-MART. All rights reserved.
 *
 * This software is the confidential and proprietary information of ABC-MART.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance wih the terms of the license agreement you entered into
 * with ABC-MART.
 */
package com.week4.concert.storage.queue.ongoing;

import com.week4.concert.domain.queue.ongoing.Ongoing;
import com.week4.concert.domain.queue.ongoing.OngoingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class OngoingCoreRepository implements OngoingRepository {

    private final OngoingJpaRepository ongoingJpaRepository;

    public OngoingCoreRepository(OngoingJpaRepository ongoingJpaRepository) {
        this.ongoingJpaRepository = ongoingJpaRepository;
    }


    @Override
    public Ongoing check(Long uesrId) {
        return ongoingJpaRepository.check(uesrId)
                .orElseThrow(() -> new EntityNotFoundException("활성화된 유저가 아닙니다."))
                .toOngoing();
    }

    @Override
    public Integer countOngoing() {
        Integer result = 0;
        Optional<List<OngoingEntity>> list = ongoingJpaRepository.countOngoing();
        if(list.isPresent()) result = list.get().size();
        return result;
    }

    @Override
    @Transactional
    public void save(Long userId) {
        ongoingJpaRepository.save(OngoingEntity.builder()
                .userId(userId)
                .status("Ongoing")
                .build());
    }

    @Override
    @Transactional
    public void updateDone(Long userId) {
        ongoingJpaRepository.updateDone(userId);
    }
}
