package com.ggplay149.concert.infrastructure.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity,String> {

    @Query("SELECT a.seatNum FROM ReservationEntity a WHERE a.reservationDate=:date AND a.title=:title")
    Optional<List<Integer>> selectReservedSeat(@Param("date")String date, @Param("title")String title);

    @Query("SELECT a FROM ReservationEntity a WHERE a.reservationNumber=:reservationNumber")
    Optional<ReservationEntity> validReservationNumber(@Param("reservationNumber")String reservationNumber);

    @Query("SELECT a FROM ReservationEntity a WHERE a.reservationNumber=:reservationNumber")
    ReservationEntity checkReservation(@Param("reservationNumber")String reservationNumber);
}
