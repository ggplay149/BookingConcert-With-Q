package com.week4.concert.integrationTest;


import com.week4.concert.application.PaymentUseCase;
import com.week4.concert.application.ReservationUseCase;
import com.week4.concert.base.lockHandler.LockHandler;
import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.payment.PaymentService;
import com.week4.concert.domain.reservation.ReservationService;
import com.week4.concert.domain.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PaymentTest {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ConcertService concertService;
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentUseCase paymentUseCase;
    @Autowired
    private LockHandler lockHandler;
    @Autowired
    private ReservationUseCase reservationUseCase;

    @Test
    @DisplayName("임시배정시간 초과로 결제실패")
    void fail_payment_becuase_time_limit() {
        //given 예약내역 data.sql로 설정
        //when
        Exception result = assertThrows(RuntimeException.class,
                () -> paymentUseCase.pay("1111.1111.111111", 2L));
        //then
        assert result.getMessage() == "존재하지 않거나 유효시간이 만료된 예약번호 입니다.";
    }
}
