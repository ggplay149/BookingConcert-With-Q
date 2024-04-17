package com.week4.concert.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ConcertResponse {

    private List<String> availableConcert;

}