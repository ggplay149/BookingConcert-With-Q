package com.week4.concert.domain.reservation;

public record Reservation(
        String id,
        String reservationDate,
        String title,
        Integer seatNum,
        Long userId,
        String finalConfirm
) {
}
