package com.week4.concert.domain.concert;

import java.util.List;

public interface ConcertRepository {

    //1) 해당 날짜, 해당 콘서트 정보 가져오기
    Concert getConcertInfo(String concert_date, String concertTitle);

    //2) 잔여좌석있는 모든 날짜의 모든 콘서트 찾기
    List<Concert> findAvailableDate();
}
