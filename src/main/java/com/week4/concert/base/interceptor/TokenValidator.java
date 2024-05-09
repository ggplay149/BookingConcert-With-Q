package com.week4.concert.base.interceptor;

import com.week4.concert.domain.queue.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TokenValidator {

    private final QueueService queueService;

    @Transactional
    public void valid(Long userId) {

    }
}
