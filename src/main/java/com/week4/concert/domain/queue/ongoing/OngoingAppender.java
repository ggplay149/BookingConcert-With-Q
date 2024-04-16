package com.week4.concert.domain.queue.ongoing;

import org.springframework.stereotype.Component;

@Component
public class OngoingAppender {

    private final OngoingRepository ongoingRepository;

    public OngoingAppender(OngoingRepository ongoingRepository) {
        this.ongoingRepository = ongoingRepository;
    }

    void save(Long userId){
        ongoingRepository.save(userId);
    }
}
