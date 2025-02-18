package com.ggplay149.concert.unitTest.domain.concert;

import com.ggplay149.concert.Fixtures;
import com.ggplay149.concert.domain.concert.Concert;
import com.ggplay149.concert.domain.concert.ConcertReader;
import com.ggplay149.concert.domain.concert.ConcertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ConcertReaderTest {

    private ConcertRepository concertRepository;
    private ConcertReader concertReader;

    @BeforeEach
    void setUp() {
        concertRepository = mock(ConcertRepository.class);
        concertReader = new ConcertReader(concertRepository);
    }

    @Test
    @DisplayName("예약가능한 콘서트 날짜별로 전부 조회")
    void findAvailableDate() {
        //given
        List<Concert> list = new ArrayList<>();
        Concert concert1 = Fixtures.concert("아이유콘서트");
        Concert concert2 = Fixtures.concert("성시경콘서트");
        list.add(concert1);
        list.add(concert2);

        given(concertRepository.findAvailableConcertAndDate()).willReturn(list);
        //when
        List<Concert> result = concertReader.findAvailableConcertAndDate();
        //then
        assert result.get(0).getCapacity()>result.get(0).getReservedCount();
        assert result.get(1).getCapacity()>result.get(1).getReservedCount();
    }

}
