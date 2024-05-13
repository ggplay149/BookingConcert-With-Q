package com.week4.concert.domain.payment;

import com.week4.concert.admin.MessageService;
import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.queue.QueueService;
import com.week4.concert.domain.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PaymentEventListener {

    private final MessageService messageService;
    private final ReservationService reservationService;
    private final ConcertService concertService;
    private final QueueService queueService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void sendMessage(PaymentEvent event){
        messageService.send();
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void removeActiveUser(PaymentEvent event){
        queueService.removeActiveUser(event.userId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void finalizeConfirmation(PaymentEvent event){
        reservationService.finalizeConfirmation(
                event.reservationNumber(),
                event.reservedConcert().title(),
                event.userId()
              );
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void increaseReservationCount(PaymentEvent event){
        concertService.increaseReservationCount(event.reservedConcert().id());
    }
}


