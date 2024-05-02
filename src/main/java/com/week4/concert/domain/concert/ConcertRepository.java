package com.week4.concert.domain.concert;

import java.util.List;

public interface ConcertRepository {

    Concert getConcertInfo(String concertDate, String concertTitle);

    List<Concert> findAvailableConcertAndDate();

    Concert getConcertInfoById(Long reservedConcertId);
}
