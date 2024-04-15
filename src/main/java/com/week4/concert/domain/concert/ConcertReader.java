package com.week4.concert.domain.concert;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConcertReader {

    private final ConcertRepository concertRepository;

    public ConcertReader(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public Concert getConcertInfo(String date, String title) {
        return concertRepository.getConcertInfo(date, title);
    }

    public List<Concert> findAvailableConcertAndDate() {
        return concertRepository.findAvailableConcertAndDate();
    }
}
