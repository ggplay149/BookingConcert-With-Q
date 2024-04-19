package com.week4.concert.infrastructure.payment;

import com.week4.concert.domain.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentCoreRepository implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public void pay(PaymentEntity payment) {
        paymentJpaRepository.save(payment);

    }
}
