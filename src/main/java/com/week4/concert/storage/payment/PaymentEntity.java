package com.week4.concert.storage.payment;

import com.week4.concert.domain.payment.Payment;
import com.week4.concert.storage.reservation.ReservationEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "payment")
@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id" ,updatable = false)
    private Long Id;

    @Column(name = "reservationNumber" ,nullable = false)
    private String reservationNumber;

    @Column(name = "userId" ,nullable = false)
    private Long userId;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    public Payment toPayment(){
        return new Payment(getId(),reservationNumber,userId);
    }
}
