package com.week4.concert.domain.payment;

public record Payment(
        Long paymentId,
        String reservationNumber,
        Long userId
) {
}
