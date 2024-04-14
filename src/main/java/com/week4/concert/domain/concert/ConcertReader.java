package com.week4.concert.domain.concert;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConcertReader {

    private final ConcertRepository concertRepository;

    public ConcertReader(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public Concert getConcertInfo(String concert_date, String concert_title) {
        return concertRepository.getConcertInfo(concert_date, concert_title);
    }

    public List<Concert> findAvailableConcertAndDate() {
        return concertRepository.findAvailableConcertAndDate();
    }
}
