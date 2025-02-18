package com.ggplay149.concert.domain.concert;

import com.ggplay149.concert.infrastructure.concert.ConcertEntity;

import java.util.List;

public interface ConcertRepository {

    ConcertEntity getConcertByTitle(String concertDate, String concertTitle);

    ConcertEntity getConcertById(Long reservedConcertId);

    List<Concert> findAvailableConcertAndDate();

    void increaseReservationCount(Long concertId);

}
