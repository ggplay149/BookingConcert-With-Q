package com.week4.concert.domain.payment.event;

import com.week4.concert.admin.MessageService;
import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.queue.QueueService;
import com.week4.concert.domain.reservation.ReservationService;
import com.week4.concert.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
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

    @Async
    @KafkaListener(topics = "payment", groupId = "payment-message")
    public void sendMessage(String data) {
        messageService.send();
        log.info(":: 전송완료 ::");
    }

    @Async
    @KafkaListener(topics = "payment", groupId = "payment-queue")
    public void removeActiveUser(String userId) {
        queueService.removeActiveUser(Long.parseLong(userId));
        log.info(":: 대기열 해제 완료 ::");
    }

}


