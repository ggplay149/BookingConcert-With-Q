package com.week4.concert.api;

import com.week4.concert.api.dto.ReservationResponse;
import com.week4.concert.useCase.ReservationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Concert API Controller", description = "예약 가능 날짜/좌석 조회 제공")
@RestController
@RequiredArgsConstructor
@RequestMapping("reservation")
public class ReservationController {

    private final ReservationUseCase reservationUseCase;


    @Operation(summary = "해당날짜/해당공연의 잔여좌석 조회", description = "Parameter example) concert_date : 20241112, concert_title : MuseConcert")
    @GetMapping("/{userId}/availableSeat")
    public ResponseEntity<ReservationResponse> findAvailableSeat(
            @PathVariable Long userId,
            @RequestParam("concert_date") String date,
            @RequestParam("concert_title") String title) {

        return ResponseEntity.ok().body(
                ReservationResponse
                        .builder()
                        .availabelSeats(reservationUseCase.selectAvailableSeat(date, title))
                        .build());

    }
}
