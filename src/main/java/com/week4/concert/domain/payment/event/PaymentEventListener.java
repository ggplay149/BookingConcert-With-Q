package com.week4.concert.domain.payment.event;

import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.reservation.ReservationService;
import com.week4.concert.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventListener {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ReservationService reservationService;
    private final ConcertService concertService;
    private final UserService userService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void publishPaymentEvent(PaymentEvent event) {

        reservationService.finalizeConfirmation(event.reservationNumber(), event.reservedConcert().title(), event.userId());

        concertService.increaseReservationCount(event.reservedConcert().id());

        userService.usePoint(event.userId(), event.reservedConcert().price());

        kafkaTemplate.send("payment",event.reservationNumber()+"/"+event.userId());
    }
}


