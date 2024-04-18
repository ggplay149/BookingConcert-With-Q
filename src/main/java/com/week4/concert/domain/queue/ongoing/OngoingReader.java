package com.week4.concert.domain.queue.ongoing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OngoingReader {

    private final OngoingRepository ongoingRepository;

    public Ongoing check(Long userId) {
        return ongoingRepository.check(userId);
    }

    public Integer countOngoing() {
        return ongoingRepository.countOngoing();
    }

}
