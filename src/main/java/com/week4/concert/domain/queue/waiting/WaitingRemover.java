package com.week4.concert.domain.queue.waiting;

public class WaitingRemover {
    private final WaitingRepository waitingRepository;

    public WaitingRemover(WaitingRepository waitingRepository) {
        this.waitingRepository = waitingRepository;
    }

    void remove(Long userId) {
        waitingRepository.deleteById(userId);
    }
}
