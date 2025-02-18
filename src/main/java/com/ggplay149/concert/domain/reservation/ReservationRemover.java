package com.ggplay149.concert.domain.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationRemover {

    private final ReservationRepository reservationRepository;

    void removeTemporaryReservation(String reservationNumber){
        reservationRepository.removeTemporaryReservation(reservationNumber);
    }
}
