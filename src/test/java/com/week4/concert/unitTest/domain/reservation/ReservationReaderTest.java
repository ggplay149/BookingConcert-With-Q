package com.week4.concert.unitTest.domain.reservation;

import com.week4.concert.Fixtures;
import com.week4.concert.domain.reservation.ReservationReader;
import com.week4.concert.domain.reservation.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ReservationReaderTest {

    private ReservationRepository reservationRepository;
    private ReservationReader reservationReader;

    @BeforeEach
    void setUp() {
        reservationRepository = mock(ReservationRepository.class);
        reservationReader = new ReservationReader(reservationRepository);
    }

    @Test
    @DisplayName("예약된 좌석 리스트 출력")
    void reservedSeat() {
        //given
        List<Integer> testList = Fixtures.reservedList("아이유콘서트");
        given(reservationRepository.selectReservedSeat(any(),any())).willReturn(testList);
        //when
        List<Integer> result =reservationReader.reservedSeat(any(),any());
        //then
        assertThat(result.get(0)).isEqualTo(1);
        assertThat(result.get(9)).isEqualTo(10);
        assertThat(result.size()).isEqualTo(10);

    }
}
