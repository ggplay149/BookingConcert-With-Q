package com.week4.concert.infrastructure.reservation;

import com.week4.concert.domain.reservation.Reservation;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "reservation", indexes = {
        @Index(name = "idx_reservation_number", columnList = "reservationNumber")
})
@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", updatable = false)
    private Long id;

    @Column(name = "reservationNumber", updatable = false,nullable = false)
    private String reservationNumber;

    @Column(name = "reservation_date", nullable = false)
    private String reservationDate;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "seat_num", nullable = false)
    private Integer seatNum;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "final_confirm", nullable = false)
    private String finalConfirm;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Reservation toReservation(){
        return new Reservation(getId(),reservationNumber,reservationDate,title,seatNum,userId,finalConfirm);
    }

}
