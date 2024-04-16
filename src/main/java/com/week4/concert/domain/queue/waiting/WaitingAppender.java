package com.week4.concert.domain.queue.waiting;
import org.springframework.stereotype.Component;

@Component
public class WaitingAppender {

    private final WaitingRepository waitingRepository;

    public WaitingAppender(WaitingRepository waitingRepository) {
        this.waitingRepository = waitingRepository;
    }

    void save(Long userId) {
        waitingRepository.save(userId);
    }
}
