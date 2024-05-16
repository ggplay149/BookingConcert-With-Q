package com.week4.concert.domain.payment.event;

import com.week4.concert.domain.concert.Concert;

public record PaymentEvent(
        String reservationNumber,
        Concert reservedConcert,
        Long userId
) {
}
