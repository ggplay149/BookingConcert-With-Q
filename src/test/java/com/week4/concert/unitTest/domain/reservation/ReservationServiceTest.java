package com.week4.concert.unitTest.domain.reservation;

import com.week4.concert.Fixtures;
import com.week4.concert.domain.reservation.ReservationAppender;
import com.week4.concert.domain.reservation.ReservationReader;
import com.week4.concert.domain.reservation.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ReservationServiceTest {


    private ReservationService reservationService;
    private ReservationReader reservationReader;
    private ReservationAppender reservationAppender;

    @BeforeEach
    void setUp() {
        reservationReader = mock(ReservationReader.class);
        reservationAppender = mock(ReservationAppender.class);
        reservationService = new ReservationService(reservationReader,reservationAppender);
    }

    @Test
    @DisplayName("예약된 좌석 리스트 출력")
    void reservedSeat() {
        //given
        List<Integer> list = Fixtures.reservedList("아이유콘서트");
        given(reservationReader.reservedSeat(any(),any())).willReturn(list);
        //when
        List<Integer> result = reservationService.reservedSeat("202020","아이유콘서트");

        //then
        assertThat(result.get(0)).isEqualTo(1);
        assertThat(result.get(9)).isEqualTo(10);
        assertThat(result.size()).isEqualTo(10);

    }

    @Test
    @DisplayName("예약 성공")
    void reserve() {
        //given
        //when
        reservationService.reserve("202020",1L,"아이유콘서트",1L,14);
        //then
    }
}
