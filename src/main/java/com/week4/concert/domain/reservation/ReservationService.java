package com.week4.concert.domain.reservation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationReader reservationReader;

    public ReservationService(ReservationReader reservationReader) {
        this.reservationReader = reservationReader;
    }

    public List<Integer> reservedSeat(String date, String title){
        return reservationReader.reservedSeat(date,title);
    }
}
