package com.week4.concert.domain.reservation;

import com.week4.concert.infrastructure.reservation.ReservationEntity;

import java.util.List;

public interface ReservationRepository {

    void reserve(ReservationEntity reservation);

    List<Integer> selectReservedSeat(String date, String title);

    void checkDuplicateReservation(String reservationNumber);

    String validateExpiration(String key);

    Boolean createTemporaryReservation(String key, String value, long ttl);

    void removeTemporaryReservation(String key);

}
