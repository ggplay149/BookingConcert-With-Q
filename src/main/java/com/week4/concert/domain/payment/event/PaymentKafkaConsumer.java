package com.week4.concert.domain.payment.event;

import com.week4.concert.admin.MessageService;
import com.week4.concert.domain.queue.QueueService;
import com.week4.concert.domain.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentKafkaConsumer {
    private final MessageService messageService;
    private final QueueService queueService;
    private final ReservationService reservationService;

    @KafkaListener(topics = "payment", groupId = "payment-message")
    public void sendMessage() {
        messageService.send();
        log.info(":: 전송완료 ::");
    }

    @KafkaListener(topics = "payment", groupId = "payment-queue")
    public void removeActiveUser(Long userId) {
        queueService.removeActiveUser(userId);
        log.info(":: 대기열 해제 완료 ::");
    }

}
