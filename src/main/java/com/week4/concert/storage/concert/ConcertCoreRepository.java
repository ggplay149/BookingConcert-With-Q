package com.week4.concert.storage.concert;

import com.week4.concert.domain.concert.Concert;
import com.week4.concert.domain.concert.ConcertRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConcertCoreRepository implements ConcertRepository {

    private final ConcertJpaRepository concertJpaRepository;

    public ConcertCoreRepository(ConcertJpaRepository concertJpaRepository) {
        this.concertJpaRepository = concertJpaRepository;
    }

    @Override
    public Concert getConcertInfo(String date, String title) {
        return concertJpaRepository.findByDateAndTitle(date,title)
                .orElseThrow(() -> new EntityNotFoundException("조회되는 콘서트가 없습니다."));

    }

    @Override
    public List<Concert> findAvailableConcertAndDate() {
        return concertJpaRepository.findAvailableConcertAndDate()
                .filter(list -> !list.isEmpty())
                .orElseThrow(()-> new EntityNotFoundException("예약가능한 콘서트 날짜가 없습니다."));
    }
}
