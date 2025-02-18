package com.ggplay149.concert.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservation {
    private Long id;
    private String reservationNumber;
    private String reservationDate;
    private String title;
    private Integer seatNum;
    private Long userId;
    private String finalConfirm;
}
