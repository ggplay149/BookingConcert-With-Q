package com.week4.concert.unitTest.domain.queue.ongoing;

import com.week4.concert.Fixtures;
import com.week4.concert.domain.queue.ongoing.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OngoingServiceTest {
    private OngoingReader ongoingReader;
    private OngoingAppender ongoingAppender;
    private OngoingRemover ongoingRemover;
    private OngoingSerivce ongoingSerivce;

    @BeforeEach
    void setUp() {
        ongoingReader = mock(OngoingReader.class);
        ongoingAppender = mock(OngoingAppender.class);
        ongoingRemover = mock(OngoingRemover.class);
        ongoingSerivce = new OngoingSerivce(ongoingReader, ongoingAppender, ongoingRemover);
    }

    @Test
    @DisplayName("Ongoing service check 성공")
    void check() {
        //given
        Long userId = 1L;
        Ongoing testUser = Fixtures.ongoing(userId);
        given(ongoingSerivce.check(userId)).willReturn(testUser);
        //when
        Ongoing result = ongoingReader.check(userId);
        //then
        assertThat(result.userId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("Ongoing service ongoing 유저 수 조회 성공")
    void countOngoing() {
        //given
        given(ongoingSerivce.countOngoing()).willReturn(5);
        //when
        Integer result = ongoingReader.countOngoing();
        //then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Ongoing service save 성공")
    void insert() {
        //given
        Long userId = 1000L;
        //when
        ongoingSerivce.insert(userId);
        //then
    }

    @Test
    @DisplayName("Ongoing service remove 성공")
    void remove() {
        //given
        Long userId = 1000L;
        //when
        ongoingSerivce.updateDone(userId);
        //then
    }
}
