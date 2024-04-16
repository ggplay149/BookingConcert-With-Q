package com.week4.concert.domain.queue.ongoing;

import org.springframework.stereotype.Component;

@Component
public class OngoingReader {

    private final OngoingRepository ongoingRepository;

    public OngoingReader(OngoingRepository ongoingRepository) {
        this.ongoingRepository = ongoingRepository;
    }

    public Ongoing check(Long userId) {
        return ongoingRepository.check(userId);
    }

    public Integer countOngoing() {
        return ongoingRepository.countOngoing();
    }

}
