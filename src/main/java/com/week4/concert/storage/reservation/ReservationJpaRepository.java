package com.week4.concert.storage.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity,String> {

    @Query("SELECT a.seatNum FROM ReservationEntity a WHERE a.reservationDate=:date AND a.title=:title")
    Optional<List<Integer>> selectReservedSeat(@Param("date")String date, @Param("title")String title);

    @Transactional
    @Modifying
    @Query("DELETE FROM ReservationEntity a " +
            "WHERE TIMESTAMPDIFF(MINUTE, a.createdAt, CURRENT_TIMESTAMP) >= 1" +
            "AND lower(a.finalConfirm) = 'n'")
    void cancelNotConfirmReservation();
}
