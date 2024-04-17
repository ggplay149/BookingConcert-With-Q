package com.week4.concert.domain.queue.ongoing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OngoingRemover {

    private final OngoingRepository ongoingRepository;

    public void updateDone(Long userId){
        ongoingRepository.updateDone(userId);
    }
}
