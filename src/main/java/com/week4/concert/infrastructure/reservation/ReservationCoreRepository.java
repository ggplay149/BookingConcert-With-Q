package com.week4.concert.infrastructure.reservation;

import com.week4.concert.domain.reservation.Reservation;
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
        return reservationJpaRepository.selectReservedSeat(date, title)
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new EntityNotFoundException("전석 예약 가능합니다."));
    }

    @Override
    public void reserve(ReservationEntity reservation) {
        try {
            reservationJpaRepository.save(reservation);
        } catch (Exception e) {
            throw new RuntimeException("이미 예약된 좌석입니다.");
        }
    }


    @Override
    public Reservation validReservationNumber(String reservationNumber) {
        return reservationJpaRepository.validReservationNumber(reservationNumber)
                .orElseThrow(() -> new EntityNotFoundException("취소되었거나 존재하지 않는 예약내역입니다."))
                .toReservation();
    }

    @Override
    public void finalConfirm(String reservationNumber) {
        reservationJpaRepository.finalConfirm(reservationNumber);
    }

    @Override
    public void checkReservation(String reservationNumber) {
        if(reservationJpaRepository.checkReservation(reservationNumber)!= null)
            throw new RuntimeException("예약된 좌석입니다.");
    }
}

