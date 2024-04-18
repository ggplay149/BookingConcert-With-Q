package com.week4.concert.IntegrationTest;


import com.week4.concert.api.useCase.PaymentUseCase;
import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.payment.PaymentService;
import com.week4.concert.domain.reservation.ReservationService;
import com.week4.concert.domain.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    @DisplayName("임시배정시간 초과로 결제실패")
    void fail_payment_becuase_time_limit() {
        //given 예약내역 data.sql로 설정
        //when
        Exception result = assertThrows(RuntimeException.class,
                () -> paymentUseCase.pay("20241112", 2L));
        //then
        assert result.getMessage() == "취소되었거나 존재하지 않는 예약내역입니다.";
    }

    @Test
    @DisplayName("잔액부족으로 결제실패")
    void fail_payment_becuase_point() {
        //given 예약내역 data.sql로 설정
        //when
        Exception result = assertThrows(RuntimeException.class,
                () -> paymentUseCase.pay("2024111254", 4L));
        //then
        assert result.getMessage() == "잔액이 부족합니다.";
    }

    @Test
    @DisplayName("결제성공 후 최종확정 확인")
    void success_payment() {
        //given
        //when
        paymentUseCase.pay("2024111259", 1L);
        //then
        assert reservationService.validReservationNumber("2024111259").finalConfirm().equals("Y");
    }
}
