package com.week4.concert.domain.queue.waiting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WaitingRemover {

    private final WaitingRepository waitingRepository;

    public void updateDone(Long id) {
        waitingRepository.updateDone(id);
    }
}
