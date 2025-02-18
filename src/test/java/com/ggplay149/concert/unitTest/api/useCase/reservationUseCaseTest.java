package com.ggplay149.concert.unitTest.api.useCase;

import com.ggplay149.concert.Fixtures;
import com.ggplay149.concert.domain.concert.Concert;
import com.ggplay149.concert.domain.concert.ConcertService;
import com.ggplay149.concert.domain.reservation.ReservationService;
import com.ggplay149.concert.application.ReservationUseCase;
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
        given(reservationService.getAvailableSeat(any(),any(),any())).willReturn(list);
        given(concertService.getConcertByTitle(any(), any())).willReturn(testConcert);

        //when
        List<Integer> result = reservationUseCase.selectAvailableSeat("202020", "아이유콘서트");

        //then
        assertThat(result.get(0)).isEqualTo(1);
        assertThat(result.get(1)).isEqualTo(2);
        assertThat(result.size()).isEqualTo(10);
    }

}
