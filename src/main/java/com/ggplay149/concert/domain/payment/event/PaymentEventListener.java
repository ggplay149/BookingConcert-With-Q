package com.ggplay149.concert.domain.payment.event;

import com.ggplay149.concert.domain.queue.QueueService;
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

    private final QueueService queueService;


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendEmailReservationSuccess(PaymentEvent event) {
        log.info(":: 예약성공 메세지 발송 ::");
    }


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void removeActiveToken(PaymentEvent event) {
        log.info(":: 성공 후 유저 권한 해제 ::");
        queueService.removeActiveUser(event.userId());
    }

}


