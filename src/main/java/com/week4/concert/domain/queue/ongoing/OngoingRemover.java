package com.week4.concert.domain.queue.ongoing;

import org.springframework.stereotype.Component;

@Component
public class OngoingRemover {
    private final OngoingRepository ongoingRepository;

    public OngoingRemover(OngoingRepository ongoingRepository) {
        this.ongoingRepository = ongoingRepository;
    }

    public void updateDone(Long userId){
        ongoingRepository.updateDone(userId);
    }
}
