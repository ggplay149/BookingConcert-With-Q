package com.week4.concert.UnitTest.api.useCase;

import com.week4.concert.Fixtures;
import com.week4.concert.domain.concert.Concert;
import com.week4.concert.domain.concert.ConcertService;
import com.week4.concert.domain.reservation.ReservationService;
import com.week4.concert.api.useCase.ReservationUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class reservationUseCaseTest {

    private ReservationService reservationService;
    private ConcertService concertService;
    private ReservationUseCase reservationUseCase;


    @BeforeEach
    void setUp() {
        reservationService = mock(ReservationService.class);
        concertService = mock(ConcertService.class);
        reservationUseCase = new ReservationUseCase(concertService,reservationService);
    }

    @Test
    @DisplayName("예약 가능한 리스트 좌석 리스트 출력")
    void selectAvailableSeat() {
        //given
        List<Integer> list = Fixtures.reservedList("아이유콘서트");
        Concert testConcert = Fixtures.concert("아이유콘서트");
        given(reservationService.availableSeat(any(),any(),any())).willReturn(list);
        given(concertService.getConcertInfo(any(), any())).willReturn(testConcert);

        //when
        List<Integer> result = reservationUseCase.selectAvailableSeat("202020", "아이유콘서트");

        //then
        assertThat(result.get(0)).isEqualTo(1);
        assertThat(result.get(1)).isEqualTo(2);
        assertThat(result.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("예약 성공")
    void reserve() {
        //given
        List<Integer> list = Fixtures.reservedList("아이유콘서트");
        Concert testConcert = Fixtures.concert("아이유콘서트");
        given(reservationService.availableSeat(any(),any(),any())).willReturn(list);
        given(concertService.getConcertInfo(any(), any())).willReturn(testConcert);

        //when
        String result = reservationUseCase.reserve("20240414","아이유콘서트",16L,25);

        //then
        assertThat(result).isEqualTo("5분간 좌석이 임시 배정되었습니다. 결제완료시 최종 확정됩니다.");
    }
}
