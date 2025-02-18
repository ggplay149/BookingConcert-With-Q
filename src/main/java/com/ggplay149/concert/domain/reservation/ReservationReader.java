package com.ggplay149.concert.domain.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationReader {

    private final ReservationRepository reservationRepository;

    public List<Integer> reservedSeat(String date, String title) {
        return reservationRepository.selectReservedSeat(date, title);    }

    public void checkDuplicateReservation(String reservationNumber) {
        reservationRepository.checkDuplicateReservation(reservationNumber);
    }

    public String validateExpiration(String key){
        return reservationRepository.validateExpiration(key);
    }
}
