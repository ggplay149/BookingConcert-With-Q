package com.week4.concert.domain.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueAppender {

    private final QueueRepository queueRepository;

    public void insertWait(Long userId){
        queueRepository.insertWait(userId);
    }

    public void insertActive(Long userId){
        queueRepository.insertActive(userId);
    }
}
