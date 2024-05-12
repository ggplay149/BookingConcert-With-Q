package com.week4.concert.domain.concert;

import java.util.List;

public interface ConcertRepository {

    Concert getConcertByTitle(String concertDate, String concertTitle);

    Concert getConcertInfoById(Long reservedConcertId);

    List<Concert> findAvailableConcertAndDate();

    void increaseReservationCount(Long concertId);

}
