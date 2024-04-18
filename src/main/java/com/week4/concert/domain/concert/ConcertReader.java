package com.week4.concert.domain.concert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConcertReader {

    private final ConcertRepository concertRepository;


    public Concert getConcertInfo(String date, String title) {
        return concertRepository.getConcertInfo(date, title);
    }

    public List<Concert> findAvailableConcertAndDate() {
        return concertRepository.findAvailableConcertAndDate();
    }
}
