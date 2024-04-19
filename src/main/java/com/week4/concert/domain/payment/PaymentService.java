package com.week4.concert.domain.payment;

import com.week4.concert.infrastructure.payment.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentAppender paymentAppender;

    public void pay(String reservationNumber, Long userId) {

        paymentAppender.pay(PaymentEntity
                .builder()
                .reservationNumber(reservationNumber)
                .userId(userId)
                .build());
    }
}
