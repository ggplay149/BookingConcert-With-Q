package com.week4.concert.domain.payment.event;

import com.week4.concert.admin.MessageService;
import com.week4.concert.domain.queue.QueueService;
import com.week4.concert.domain.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventListener {

    private final MessageService messageService;
    private final QueueService queueService;
    private final ReservationService reservationService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void sendMessage(PaymentEvent event) {
        messageService.send();
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void removeActiveUser(PaymentEvent event) {
        queueService.removeActiveUser(event.userId());
        log.info(" :: 대기열 해제 완료 ::");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void removeTemporaryReservation(PaymentEvent event) {
        reservationService.removeTemporaryReservation(event.reservationNumber());
        log.info(" :: 임시예약 해제 완료 ::");
    }
}


