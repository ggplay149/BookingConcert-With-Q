package com.week4.concert.domain.queue.waiting;

import org.springframework.stereotype.Component;

@Component
public class WaitingRemover {
    private final WaitingRepository waitingRepository;

    public WaitingRemover(WaitingRepository waitingRepository) {
        this.waitingRepository = waitingRepository;
    }

    public void updateDone(Long id) {
        waitingRepository.updateDone(id);
    }
}
