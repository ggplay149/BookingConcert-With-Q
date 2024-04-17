package com.week4.concert.storage.reservation;

import com.week4.concert.domain.reservation.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationCoreRepository implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository ;

    public ReservationCoreRepository(ReservationJpaRepository reservationJpaRepository) {
        this.reservationJpaRepository = reservationJpaRepository;
    }

    @Override
    public List<Integer> selectReservedSeat(String date, String title) {
        return reservationJpaRepository.selectReservedSeat(date,title)
                .filter(list -> !list.isEmpty())
                .orElseThrow(()-> new EntityNotFoundException("전석 예약 가능합니다."));
    }
}
