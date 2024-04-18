package com.week4.concert.domain.reservation;

public record Reservation(
        Long id,
        String reservationNumber,
        String reservationDate,
        String title,
        Integer seatNum,
        Long userId,
        String finalConfirm
) {
}
