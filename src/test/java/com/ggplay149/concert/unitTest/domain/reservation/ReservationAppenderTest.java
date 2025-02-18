package com.ggplay149.concert.unitTest.domain.reservation;

import com.ggplay149.concert.domain.reservation.ReservationAppender;
import com.ggplay149.concert.domain.reservation.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class ReservationAppenderTest {

    private ReservationRepository reservationRepository;
    private ReservationAppender reservationAppender;

    @BeforeEach
    void setUp() {
        reservationRepository = mock(ReservationRepository.class);
        reservationAppender = new ReservationAppender(reservationRepository);
    }

    @Test
    @DisplayName("좌석 예약 성공")
    void reserve() {
        //given
        //when
        reservationAppender.reserve(any());
        //then
    }
}
