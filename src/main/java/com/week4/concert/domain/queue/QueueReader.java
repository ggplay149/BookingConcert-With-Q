package com.week4.concert.domain.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueReader {

    private final QueueRepository queueRepository;

    public Boolean checkUserStatus(Long userId) {
        return queueRepository.checkUserStatus(userId);
    }

    public Long countActive() {
        return queueRepository.countActive();
    }

    public String[] getTopNFromWait(Long topN){
        return queueRepository.getTopNFromWait(topN);
    }

}
