package com.ggplay149.concert.domain.reservation;

import com.ggplay149.concert.infrastructure.reservation.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationAppender {

    private final ReservationRepository reservationRepository;

    public void reserve (ReservationEntity reservation) {
        reservationRepository.reserve(reservation);
    }

    public Boolean createTemporaryReservation(String key, String value, long ttl) {
        return reservationRepository.createTemporaryReservation(key, value, ttl);
    }

}
