package com.ggplay149.concert.integrationTest;


import com.ggplay149.concert.application.PaymentUseCase;
import com.ggplay149.concert.application.ReservationUseCase;
import com.ggplay149.concert.base.lockHandler.LockHandler;
import com.ggplay149.concert.domain.concert.ConcertService;
import com.ggplay149.concert.domain.payment.PaymentService;
import com.ggplay149.concert.domain.reservation.ReservationService;
import com.ggplay149.concert.domain.user.UserService;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void resetRedis() {
        lockHandler.reset();
    }

    @Test
    @DisplayName("잘못된 예약번호로 결제실패")
    void fail_payment_becuase_wrong_reservation_number() {

        //given

        //when
        Exception result = assertThrows(RuntimeException.class,
                () -> paymentUseCase.pay("wrongReservationNumber", 2L));

        //then
        assert result.getMessage() == "존재하지 않거나 유효시간이 만료된 예약번호 입니다.";
    }

    @Test
    @DisplayName("잔액부족으로 결제실패")
    void fail_payment_becuase_not_enough_point() {

        //given : 신규 예약생성, data.sql로 user 2포인트 보유설정
        String temp = reservationUseCase.createTemporaryReservation("20241112","MuseConcert",4L,34);

        //when
        String reservationNumber = "20241112.5.34";

        Exception result = assertThrows(RuntimeException.class,
                () -> paymentUseCase.pay(reservationNumber, 4L));

        //then
        assert result.getMessage() == "잔액이 부족합니다.";
    }

    @Test
    @DisplayName("결제 정상 성공")
    void success_payment() {

        //given : 신규 예약생성
        reservationUseCase.createTemporaryReservation("20241112","MuseConcert",1L,48);

        //when
        String reservationNumber = "20241112.5.48";

        String result = paymentUseCase.pay(reservationNumber, 1L);

        //then
        assert result == "정상 결제되었습니다. 예약이 확정되었습니다.";
    }
}
