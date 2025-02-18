package com.ggplay149.concert.infrastructure.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity,Long> {
}
