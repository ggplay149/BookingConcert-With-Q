package com.ggplay149.concert.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
