package com.week4.concert.storage.reservation;

import com.week4.concert.domain.reservation.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationCoreRepository implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public List<Integer> selectReservedSeat(String date, String title) {
        return reservationJpaRepository.selectReservedSeat(date,title)
                .filter(list -> !list.isEmpty())
                .orElseThrow(()-> new EntityNotFoundException("전석 예약 가능합니다."));
    }
}
