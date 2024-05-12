package com.week4.concert.domain.concert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ConcertService {

    private final ConcertReader concertReader;
    private final ConcertAppender concertAppender;


    public Concert getConcertByTitle(String date, String title) {
        return concertReader.getConcertByTitle(date, title);
    }

    public Concert getConcertById(Long reservedConcertId) {
        return concertReader.getConcertInfoById(reservedConcertId);
    }

    public void increaseReservationCount(Long concertId) {
        concertAppender.increaseReservationCount(concertId);
    }

    public List<String> getConcertByTitle() {
        List<String> list = new ArrayList<>();
        for (Concert c : concertReader.findAvailableConcertAndDate()) {
            String form = "[ " + c.date() + " / " + c.title() + " ]";
            list.add(form);
        }
        return list;
    }
}
