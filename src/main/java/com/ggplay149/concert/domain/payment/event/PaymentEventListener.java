package com.ggplay149.concert.domain.payment.event;

import com.ggplay149.concert.domain.concert.ConcertService;
import com.ggplay149.concert.domain.reservation.ReservationService;
import com.ggplay149.concert.domain.user.UserService;
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

        reservationService.finalizeConfirmation(event.reservationNumber(), event.reservedConcert().getTitle(), event.userId());

        concertService.increaseReservationCount(event.reservedConcert().getId());

        userService.usePoint(event.userId(), event.reservedConcert().getPrice());

        kafkaTemplate.send("payment",event.reservationNumber()+"/"+event.userId());
    }
}


