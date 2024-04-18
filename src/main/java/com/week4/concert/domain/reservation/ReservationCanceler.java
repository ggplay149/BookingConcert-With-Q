package com.week4.concert.domain.reservation;

import com.week4.concert.storage.reservation.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationCanceler {

    private final ReservationRepository reservationRepository;

    public void cancelNotConfirmReservation () { reservationRepository.cancelNotConfirmReservation(); }
}
