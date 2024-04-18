package com.week4.concert.api.controller;

import com.week4.concert.api.dto.ReservationResponse;
import com.week4.concert.useCase.ReservationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Reservation API Controller", description = "해당 콘서트 남은좌석 조회 / 예약 기능 제공")
@RestController
@RequiredArgsConstructor
@RequestMapping("reservation")
public class ReservationController {

    private final ReservationUseCase reservationUseCase;


    @Operation(summary = "해당날짜/해당공연의 잔여좌석 조회", description = "Parameter example) date : 20241112, title : MuseConcert")
    @GetMapping("/{userId}/availableSeat")
    public ResponseEntity<ReservationResponse> findAvailableSeat(
            @PathVariable Long userId,
            @RequestParam("date") String date,
            @RequestParam("title") String title) {

        return ResponseEntity.ok().body(
                ReservationResponse
                        .builder()
                        .availabelSeats(reservationUseCase.selectAvailableSeat(date, title))
                        .build());
    }

    @Operation(summary = "해당날짜/해당공연 예약", description = "Parameter example) date : 20240504, title : PsyConcert")
    @PostMapping("/{userId}/reserve")
    public ResponseEntity<ReservationResponse> reserve(
            @PathVariable Long userId,
            @RequestParam("date") String date,
            @RequestParam("title") String title,
            @RequestParam("seatNum") Integer seatNum){

        return ResponseEntity.ok().body(
                ReservationResponse
                        .builder()
                        .message(reservationUseCase.reserve(date,title,userId,seatNum))
                        .build());
    }
}
