package com.week4.concert.storage.concert;

import com.week4.concert.domain.concert.Concert;
import com.week4.concert.domain.concert.ConcertRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConcertCoreRepository implements ConcertRepository {

    private final ConcertJpaRepository concertJpaRepository;

    public ConcertCoreRepository(ConcertJpaRepository concertJpaRepository) {
        this.concertJpaRepository = concertJpaRepository;
    }

    @Override
    public Concert getConcertInfo(String concert_date, String concert_title) {
        return concertJpaRepository.getConcertInfo(concert_date,concert_title);
    }

    @Override
    public List<Concert> findAvailableDate() {
        return concertJpaRepository.findAvailableDate();
    }
}
