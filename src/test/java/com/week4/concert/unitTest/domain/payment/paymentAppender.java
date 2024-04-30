package com.week4.concert.unitTest.domain.payment;

import com.week4.concert.domain.payment.PaymentAppender;
import com.week4.concert.domain.payment.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class paymentAppender {

    private PaymentAppender paymentAppender;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        paymentAppender = new PaymentAppender(paymentRepository);
    }

    @Test
    @DisplayName("좌석예약 성공")
    void success_pay() {
        //given
        // when
        paymentAppender.pay(any());
        //then
    }
}
