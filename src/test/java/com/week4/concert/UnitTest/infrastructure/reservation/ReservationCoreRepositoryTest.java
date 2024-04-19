package com.week4.concert.UnitTest.infrastructure.reservation;

import com.week4.concert.infrastructure.reservation.ReservationCoreRepository;
import com.week4.concert.infrastructure.reservation.ReservationJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ReservationCoreRepositoryTest {

    private ReservationCoreRepository reservationCoreRepository;
    private ReservationJpaRepository reservationJpaRepository;

    @BeforeEach
    void setUp() {
        reservationJpaRepository = mock(ReservationJpaRepository.class);
        reservationCoreRepository = new ReservationCoreRepository(reservationJpaRepository);
    }

    String concertDate = "20230101";
    String concertTitle = "없는콘서트";
    Integer seatNum = 12;

    @Test
    @DisplayName("날짜와 제목으로 조회했을 때, 예약된 좌석 없음")
    void when_not_found_reserved_seat_then_error() {
        //given
        given(reservationJpaRepository.selectReservedSeat(concertDate,concertTitle)).willReturn(Optional.empty());
        // when
        Exception e = assertThrows(EntityNotFoundException.class, () -> reservationCoreRepository.selectReservedSeat(concertDate,concertTitle));
        //then
        assertThat(e.getMessage()).isEqualTo("전석 예약 가능합니다.");
    }

    @Test
    @DisplayName("좌석예약 성공")
    void success_reservation() {
        //given
        // when
        reservationJpaRepository.save(any());
        //then
    }
}
