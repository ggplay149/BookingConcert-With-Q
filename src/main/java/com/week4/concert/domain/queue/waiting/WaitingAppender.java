package com.week4.concert.domain.queue.waiting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WaitingAppender {

    private final WaitingRepository waitingRepository;

    public void save(Long userId) {
        waitingRepository.save(userId);
    }
}
