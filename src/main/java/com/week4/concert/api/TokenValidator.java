package com.week4.concert.api;

import com.week4.concert.domain.queue.ongoing.OngoingSerivce;

public class TokenValidator {

    private final OngoingSerivce ongoingSerivce;

    public TokenValidator(OngoingSerivce ongoingSerivce) {
        this.ongoingSerivce = ongoingSerivce;
    }


}
