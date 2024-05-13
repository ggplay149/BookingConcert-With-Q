package com.week4.concert.domain.concert;

import com.week4.concert.infrastructure.concert.ConcertEntity;
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


    public Concert getConcertByTitle(String date, String title) {
        return concertReader.getConcertByTitle(date, title).toConcert();
    }

    public Concert getConcertById(Long reservedConcertId) {
        return concertReader.getConcertById(reservedConcertId).toConcert();
    }

    public void increaseReservationCount(Long concertId) {
        ConcertEntity concert = concertReader.getConcertById(concertId);
        concert.setReservedCount(concert.getReservedCount()+1);
    }

    public List<String> showAvailableConcertList() {
        List<String> list = new ArrayList<>();
        for (Concert c : concertReader.findAvailableConcertAndDate()) {
            String form = "[ " + c.date() + " / " + c.title() + " ]";
            list.add(form);
        }
        return list;
    }
}
