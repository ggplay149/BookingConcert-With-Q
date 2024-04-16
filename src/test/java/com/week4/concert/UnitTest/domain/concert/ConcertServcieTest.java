package com.week4.concert.UnitTest.domain.concert;

import com.week4.concert.Fixtures;
import com.week4.concert.domain.concert.Concert;
import com.week4.concert.domain.concert.ConcertReader;
import com.week4.concert.domain.concert.ConcertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ConcertServcieTest {

    private ConcertService concertService;
    private ConcertReader concertReader;

    @BeforeEach
    void setUp() {
        concertReader = mock(ConcertReader.class);
        concertService = new ConcertService(concertReader);
    }

    @Test
    @DisplayName("예약가능한 콘서트 날짜별 형식화해서 출력")
    void showAvailableConcertList() {
        //given
        List<Concert> list = new ArrayList<>();
        list.add(Fixtures.concert("아이유콘서트"));
        list.add(Fixtures.concert("실리카겔콘서트"));

        given(concertReader.findAvailableConcertAndDate()).willReturn(list);
        //when
        List<String> result = concertService.showAvailableConcertList();
        //then
        assertThat(result.get(0)).isEqualTo("[ 20240414 / 아이유콘서트 ]");
        assertThat(result.get(1)).isEqualTo("[ 20240516 / 실리카겔콘서트 ]");
    }
}
