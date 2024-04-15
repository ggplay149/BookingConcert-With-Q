package com.week4.concert.storage.reservation;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    @DisplayName("날짜와 제목으로 조회했을 때, 예약된 좌석 없음")
    void when_not_found_reserved_seat_then_error() {
        //given
        // when
        Exception e = assertThrows(EntityNotFoundException.class, () -> reservationCoreRepository.selectReservedSeat(concertDate,concertTitle));
        //then
        assertThat(e.getMessage()).isEqualTo("전석 예약 가능합니다.");
    }

}
