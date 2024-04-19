package com.week4.concert.domain.payment;

import com.week4.concert.infrastructure.payment.PaymentEntity;

public interface PaymentRepository {

    void pay(PaymentEntity payment);

}
