package com.week4.concert.domain.concert;

import com.week4.concert.infrastructure.concert.ConcertEntity;

import java.util.List;

public interface ConcertRepository {

    ConcertEntity getConcertByTitle(String concertDate, String concertTitle);

    ConcertEntity getConcertById(Long reservedConcertId);

    List<Concert> findAvailableConcertAndDate();

    void increaseReservationCount(Long concertId);

}
