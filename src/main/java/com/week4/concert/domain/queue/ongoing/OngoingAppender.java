package com.week4.concert.domain.queue.ongoing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OngoingAppender {

    private final OngoingRepository ongoingRepository;

    public void save(Long userId){
        ongoingRepository.save(userId);
    }
}
