package com.ggplay149.concert.integrationTest;

import com.ggplay149.concert.application.PaymentUseCase;
import com.ggplay149.concert.base.lockHandler.LockHandler;
import com.ggplay149.concert.domain.queue.QueueService;
import com.ggplay149.concert.domain.reservation.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventTest {

    @Autowired
    private LockHandler lockHandler;

    @Autowired
    private PaymentUseCase paymentUseCase;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private QueueService queueService;



    @BeforeEach
    void resetRedis() {
        lockHandler.reset();
    }

    @Test
    @DisplayName("이벤트 정상 발행/처리 테스트")
    void message_producing_and_consuming(){
        reservationService.createTemporaryReservation("20241112.5.21",1L);
        paymentUseCase.pay("20241112.5.21",1L);
        assert queueService.checkUserStatus(1L) == "대기중인 유저가 아닙니다.";
    }

}
