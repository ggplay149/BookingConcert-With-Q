package com.week4.concert.api;

import com.week4.concert.api.dto.ConcertResponse;
import com.week4.concert.domain.concert.ConcertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Concert API Controller", description = "예약 가능 날짜/좌석 조회 제공")
@RestController
@RequiredArgsConstructor
@RequestMapping("concert")
public class ConcertController {

    private final ConcertService concertService;

    @Operation(summary = "좌석이 남아있는 콘서트 정보 조회", description = "Parameter example) parameter 없음")
    @GetMapping("/{userId}/availableConcert")
    public ResponseEntity<ConcertResponse> getAvailableConcertList(@PathVariable Long userId) {

        return ResponseEntity.ok().body(ConcertResponse
                .builder()
                .availableConcert(concertService.showAvailableConcertList())
                .build());

    }
}