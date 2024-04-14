package com.week4.concert.domain.concert;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ConcertReader {

    private final ConcertRepository concertRepository;

    public ConcertReader(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    //1) 해당 날짜, 해당 콘서트 정보 가져오기
    public Concert getConcertInfo(String concert_date, String concert_title) {

        //조회 파라미터 잘못 입력
        Concert result = Optional.ofNullable(concertRepository.getConcertInfo(concert_date, concert_title))
                .orElseThrow(() -> new EntityNotFoundException("조회되는 콘서트가 없습니다."));

        return result;
    }

    //2) 잔여좌석있는 모든 날짜의 모든 콘서트 찾기
    public List<String> findAvailableDate() {
        List<Concert> selectResult = Optional.ofNullable(concertRepository.findAvailableDate())
                .filter(list -> !list.isEmpty())
                .orElseThrow(()-> new EntityNotFoundException("예약가능한 콘서트 날짜가 없습니다."));

        /* 잔여 좌석있는 콘서트 날짜랑 제목이랑 붙여서 보여주기 */
        List<String> result = new ArrayList<>();
        for (Concert c : selectResult) {
            result.add("[ " + c.date() + " / " + c.title() + " ]");
        }
        return result;
    }

}
