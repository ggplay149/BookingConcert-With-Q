package com.week4.concert.application;

import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor

public class ReservationUseCase {

    private final ConcertService concertService;
    private final ReservationService reservationService;

    @Transactional(readOnly = true)
    public List<Integer> selectAvailableSeat(String date, String title) {

        Integer capactiy = concertService.getConcertByTitle(date, title).capacity();

        List<Integer> availableSeat = reservationService.getAvailableSeat(date, title, capactiy);

        return availableSeat;
    }


    @Transactional
    public String createTemporaryReservation(String date, String title, Long userId, Integer seatNum) {

        Long concertId = concertService.getConcertByTitle(date, title).id();

        String reservationNumber = date+"."+concertId+"."+seatNum;

        reservationService.checkDuplicateReservation(reservationNumber);

        reservationService.createTemporaryReservation(reservationNumber,concertId);

        return "[ 예약번호 : "+reservationNumber+" ] 5분간 좌석이 임시 배정되었습니다. 결제완료시 최종 확정됩니다.";

    }
}

