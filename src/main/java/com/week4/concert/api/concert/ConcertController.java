package com.week4.concert.api.concert;

import com.week4.concert.api.concert.dto.ConcertResponse;
import com.week4.concert.domain.concert.ConcertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Concert API Controller", description = "예약 가능 날짜/좌석 조회 제공")
@RestController("concert")
public class ConcertController {

    private final ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }


    @Operation(summary = "좌석이 남아있는 콘서트 정보 조회", description = "Parameter example) parameter 없음")
    @GetMapping("/availableConcert")
    public ResponseEntity<ConcertResponse> getAvailableConcertList() {
        return ResponseEntity.ok().body(ConcertResponse.builder()
                .availableConcert(concertService.showAvailableConcertList())
                .build());
    }

//    @Operation(summary = "해당날짜/해당공연의 잔여좌석 조회", description = "Parameter example) concert_date : 20241112, concert_title : MuseConcert")
//    @GetMapping("/availableSeat")
//    public ResponseEntity<ConcertResponse> findAvailableSeat(
//            @RequestParam("concert_date") String concert_date, @RequestParam("concert_title") String concert_title) {
//        return ResponseEntity.ok().body();
//    }

}