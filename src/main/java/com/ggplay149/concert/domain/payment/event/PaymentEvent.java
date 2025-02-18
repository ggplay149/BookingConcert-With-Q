package com.ggplay149.concert.domain.payment.event;

import com.ggplay149.concert.domain.concert.Concert;

public record PaymentEvent(
        String reservationNumber,
        Concert reservedConcert,
        Long userId
) {
}
