package com.ggplay149.concert.domain.concert;

import com.ggplay149.concert.infrastructure.concert.ConcertEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConcertReader {

    private final ConcertRepository concertRepository;


    public ConcertEntity getConcertByTitle(String date, String title) {
        return concertRepository.getConcertByTitle(date, title);
    }

    public ConcertEntity getConcertById(Long reservedConcertId) {
        return concertRepository.getConcertById(reservedConcertId);
    }

    public List<Concert> findAvailableConcertAndDate() {
        return concertRepository.findAvailableConcertAndDate();
    }

}
