package com.week4.concert.base.interceptor;

import com.week4.concert.domain.queue.QueueReader;
import com.week4.concert.domain.queue.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TokenValidator {

    private final QueueReader queueReader;

    @Transactional
    public void valid(Long userId) {
        if(!queueReader.checkUserStatus(userId,"Active")){
            throw new RuntimeException("활성화된 유저가 아닙니다.");
        }
    }
}
