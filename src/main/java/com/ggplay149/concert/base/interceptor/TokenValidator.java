package com.ggplay149.concert.base.interceptor;

import com.ggplay149.concert.domain.queue.QueueReader;
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
