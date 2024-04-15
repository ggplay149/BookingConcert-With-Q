package com.week4.concert.domain.reservation;

import java.util.List;

public interface ReservationRepository {

    List<Integer> selectReservedSeat(String date, String title);
}
