package com.week4.concert.domain.payment;

import com.week4.concert.storage.payment.PaymentEntity;

public interface PaymentRepository {

    void pay(PaymentEntity payment);

}
