package com.week4.concert.storage.reservation;

import com.week4.concert.domain.reservation.Reservation;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "reservation")
@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationEntity {

    @Id
    @Column(name = "reservation_id", updatable = false)
    private String reservationId;

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

    public Reservation toReservation(){
        return new Reservation(getReservationId(),reservationDate,title,seatNum,userId,finalConfirm);
    }

}
