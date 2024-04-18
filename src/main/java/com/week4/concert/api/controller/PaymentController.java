package com.week4.concert.api.controller;

import com.week4.concert.api.dto.PaymentResponse;
import com.week4.concert.api.useCase.PaymentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payment API Controller", description = "결제 기능 제공")
@RestController
@RequiredArgsConstructor
@RequestMapping("payment")
public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    @Operation(summary = "임시예약 결제 후 확정", description = "Parameter example) 예약번호 생성후 입력")
    @PostMapping("/{userId}/pay")
    public ResponseEntity<PaymentResponse> pay(
            @PathVariable Long userId,
            @RequestParam("ReservationNumber") String reservationNumber) {
        return ResponseEntity.ok().body(PaymentResponse
                .builder()
                .message(paymentUseCase.pay(reservationNumber, userId))
                .build());
    }
}
