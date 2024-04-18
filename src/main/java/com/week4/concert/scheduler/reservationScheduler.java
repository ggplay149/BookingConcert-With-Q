package com.week4.concert.scheduler;

import com.week4.concert.domain.reservation.ReservationCanceler;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class reservationScheduler{

    private final ReservationCanceler reservationCanceler;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void cancel_not_confirm_reservation(){
        reservationCanceler.cancelNotConfirmReservation();
    }

}
