package com.week4.concert.unitTest.domain.payment;

import com.week4.concert.domain.payment.PaymentAppender;
import com.week4.concert.domain.payment.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class paymentServiceTest {

    private PaymentService paymentService;
    private PaymentAppender paymentAppender;

    @BeforeEach
    void setUp() {
        paymentAppender = mock(PaymentAppender.class);
        paymentService = new PaymentService(paymentAppender);
    }

    @Test
    @DisplayName("좌석예약 성공")
    void success_pay() {
        //given
        // when
        paymentService.pay("20204154",1L);
        //then
    }
}
