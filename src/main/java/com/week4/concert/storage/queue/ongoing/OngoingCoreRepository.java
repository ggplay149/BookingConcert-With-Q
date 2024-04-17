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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OngoingCoreRepository implements OngoingRepository {

    private final OngoingJpaRepository ongoingJpaRepository;

    public OngoingCoreRepository(OngoingJpaRepository ongoingJpaRepository) {
        this.ongoingJpaRepository = ongoingJpaRepository;
    }


    @Override
    public Ongoing check(Long uesrId) {
        return ongoingJpaRepository.findByUserId(uesrId)
                .orElseThrow(()->new EntityNotFoundException("활성화된 유저가 아닙니다."))
                .toOngoing();
    }

    @Override
    public Integer countOngoing() {
        return ongoingJpaRepository.countOngoing();
    }

    @Override
    public void save(Long userId) {
        ongoingJpaRepository.save(OngoingEntity.builder().userId(userId).build());
    }

    @Override
    public void deleteById(Long userId) {
        ongoingJpaRepository.deleteById(userId);
    }
}
