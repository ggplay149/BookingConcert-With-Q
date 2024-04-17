package com.week4.concert.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Integer> availabelSeats;
}
