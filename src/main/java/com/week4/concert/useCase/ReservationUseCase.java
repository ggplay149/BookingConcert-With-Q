package com.week4.concert.useCase;

import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationUseCase {

    private final ConcertService concertService;
    private final ReservationService reservationService;

    public List<Integer> selectAvailableSeat(String date, String title) {

        Integer capactiy = concertService.getConcertInfo(date, title).capacity();

        List<Integer> availableSeat = reservationService.availableSeat(date, title, capactiy);

        return availableSeat;
    }


    public String reserve(String date, String title, Long userId, Integer seatNum) {

        Long concertId = concertService.getConcertInfo(date, title).id();

        reservationService.reserve(date, concertId, title, userId, seatNum);

        return "5분간 좌석이 임시 배정되었습니다. 결제완료시 최종 확정됩니다.";

    }
}
