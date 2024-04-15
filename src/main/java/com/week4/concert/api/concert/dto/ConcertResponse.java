package com.week4.concert.api.concert.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ConcertResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> availableConcert;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Integer> availabelSeats;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

}