package com.week4.concert.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer point;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
