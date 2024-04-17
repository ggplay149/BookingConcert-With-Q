package com.week4.concert.useCase;

import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.reservation.ReservationService;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ReservationUseCase {

    private final ConcertService concertService;
    private final ReservationService reservationService;

    public ReservationUseCase(ConcertService concertService, ReservationService reservationService) {
        this.concertService = concertService;
        this.reservationService = reservationService;
    }

    public List<Integer> selectAvailableSeat(String date, String title){

        Integer capactiy = concertService.getConcertInfo(date,title).capacity();

        List<Integer> availableSeat = reservationService.availableSeat(date,title,capactiy);

        return availableSeat;
    }
}
