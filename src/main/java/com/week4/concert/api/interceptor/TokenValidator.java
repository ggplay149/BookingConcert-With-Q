package com.week4.concert.api.interceptor;

import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenValidator {

    @Autowired
    private OngoingSerivce ongoingSerivce;

    public void valid(Long userId) {
        ongoingSerivce.check(userId);
    }
}
