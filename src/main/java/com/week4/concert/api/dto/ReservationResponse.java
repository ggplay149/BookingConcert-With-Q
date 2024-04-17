package com.week4.concert.api.dto;

import lombok.*;

import java.util.List;

@Setter
@Builder
public class ReservationResponse {

    private List<Integer> availabelSeats;
}
