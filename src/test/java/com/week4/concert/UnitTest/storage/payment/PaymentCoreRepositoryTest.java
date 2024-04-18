package com.week4.concert.UnitTest.storage.payment;

import com.week4.concert.storage.payment.PaymentCoreRepository;
import com.week4.concert.storage.payment.PaymentJpaRepository;
import com.week4.concert.storage.reservation.ReservationCoreRepository;
import com.week4.concert.storage.reservation.ReservationJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PaymentCoreRepositoryTest {

    private PaymentCoreRepository paymentCoreRepository;
    private PaymentJpaRepository paymentJpaRepository;

    @BeforeEach
    void setUp() {
        paymentJpaRepository = mock(PaymentJpaRepository.class);
        paymentCoreRepository = new PaymentCoreRepository(paymentJpaRepository);
    }

    @Test
    @DisplayName("좌석예약 성공")
    void success_pay() {
        //given
        // when
        paymentCoreRepository.pay(any());
        //then
    }
}
