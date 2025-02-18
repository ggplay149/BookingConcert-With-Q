package com.ggplay149.concert.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {
    private Long paymentId;
    private String reservationNumber;
    private Long userId;
}
