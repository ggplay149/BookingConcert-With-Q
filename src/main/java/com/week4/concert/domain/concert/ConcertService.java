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
