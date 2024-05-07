package com.week4.concert.base.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TokenValidator {


    @Transactional
    public void valid(Long userId) {

    }
}
