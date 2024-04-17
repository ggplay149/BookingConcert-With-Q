package com.week4.concert.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class ReservationResponse {

    private List<Integer> availabelSeats;

}
