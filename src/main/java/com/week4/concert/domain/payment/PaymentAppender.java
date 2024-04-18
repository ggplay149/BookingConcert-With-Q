package com.week4.concert.domain.payment;

import com.week4.concert.storage.payment.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentAppender {

    private final  PaymentRepository paymentRepository;

    public void pay (PaymentEntity payment){ paymentRepository.pay(payment);}
}
