package com.week4.concert.domain.concert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcertAppender {

    private ConcertRepository concertRepository;

    public void increaseReservationCount(Long concertId){
        concertRepository.increaseReservationCount(concertId);
    }
}
