package com.ggplay149.concert.domain.payment;

import com.ggplay149.concert.infrastructure.payment.PaymentEntity;

public interface PaymentRepository {

    void pay(PaymentEntity payment);

}
