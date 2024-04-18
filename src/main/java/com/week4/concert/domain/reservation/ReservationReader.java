package com.week4.concert.domain.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationReader {

    private final ReservationRepository reservationRepository;

    public List<Integer> reservedSeat(String date, String title) {
        return reservationRepository.selectReservedSeat(date, title);
    }

    public Reservation validReservationNumber(String reservationNumber) {
        return reservationRepository.validReservationNumber(reservationNumber);
    }
}
