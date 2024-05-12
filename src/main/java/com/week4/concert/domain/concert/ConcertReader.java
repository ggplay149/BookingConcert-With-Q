package com.week4.concert.domain.concert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConcertReader {

    private final ConcertRepository concertRepository;


    public Concert getConcertByTitle(String date, String title) {
        return concertRepository.getConcertByTitle(date, title);
    }

    public Concert getConcertInfoById(Long reservedConcertId) {
        return concertRepository.getConcertInfoById(reservedConcertId);
    }

    public List<Concert> findAvailableConcertAndDate() {
        return concertRepository.findAvailableConcertAndDate();
    }

}
