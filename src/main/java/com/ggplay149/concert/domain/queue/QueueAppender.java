package com.ggplay149.concert.domain.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueAppender {

    private final QueueRepository queueRepository;

    public void insert(Long userId,String key) { queueRepository.insert(userId, key); }
}
