package com.week4.concert.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Setter
@Builder
public class ConcertResponse {

    private List<String> availableConcert;

}