package com.week4.concert.domain.payment;

import com.week4.concert.storage.reservation.ReservationEntity;

public record Payment(
        Long paymentId,
        String reservationNumber,
        Long userId
) {
}
