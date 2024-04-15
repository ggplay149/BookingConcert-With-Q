package com.week4.concert.domain.reservation;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationReader {

    private final  ReservationRepository reservationRepository;

    public ReservationReader(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Integer> reservedSeat (String date, String title){
        return reservationRepository.selectReservedSeat(date, title);
    }
}
