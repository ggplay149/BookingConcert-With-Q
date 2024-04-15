package com.week4.concert.domain.concert;

import com.week4.concert.api.concert.dto.ConcertResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConcertService {

    private final ConcertReader concertReader;

    public ConcertService(ConcertReader concertReader) {
        this.concertReader = concertReader;
    }

    public Concert getConcertInfo(String date, String title){
        return concertReader.getConcertInfo(date,title);
    }

    public List<String> showAvailableConcertList() {
        List<String> list = new ArrayList<>();
        for(Concert c : concertReader.findAvailableConcertAndDate()){
            String form = "[ "+c.date()+" / " +c.title()+" ]";
            list.add(form);
        }
        return list;
    }
}
