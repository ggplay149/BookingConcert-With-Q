package com.week4.concert.UnitTest.storage.concert;

import com.week4.concert.domain.concert.Concert;
import com.week4.concert.domain.user.User;
import com.week4.concert.storage.concert.ConcertCoreRepository;
import com.week4.concert.storage.concert.ConcertJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ConcertCoreRepositoryTest {

    private ConcertCoreRepository concertCoreRepository;
    private ConcertJpaRepository concertJpaRepository;

    @BeforeEach
    void setUp() {
        concertJpaRepository = mock(ConcertJpaRepository.class);
        concertCoreRepository = new ConcertCoreRepository(concertJpaRepository);
    }

    String concertDate = "20230101";
    String concertTitle = "없는콘서트";

    @Test
    @DisplayName("날짜와 제목으로 조회했을때, 없는 콘서트")
    void when_not_found_concert_Info_then_error() {
        //given
        given(concertJpaRepository.findByDateAndTitle(concertDate,concertTitle)).willReturn(Optional.empty());

        //when
        Exception e = assertThrows(EntityNotFoundException.class, () -> concertCoreRepository.getConcertInfo(concertDate, concertTitle));
        //then
        assertThat(e.getMessage()).isEqualTo("조회되는 콘서트가 없습니다.");
    }

    @Test
    @DisplayName("예약가능 콘서트가 없음")
    void when_not_found_available_concert_then_error() {
        //given
        given(concertJpaRepository.findAvailableConcertAndDate()).willReturn(Optional.empty());
        //when
        Exception e = assertThrows(EntityNotFoundException.class, () -> concertCoreRepository.findAvailableConcertAndDate());
        //then
        assertThat(e.getMessage()).isEqualTo("예약가능한 콘서트 날짜가 없습니다.");
    }
}
