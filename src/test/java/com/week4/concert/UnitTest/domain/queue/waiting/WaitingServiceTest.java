package com.week4.concert.UnitTest.domain.queue.waiting;

import com.week4.concert.Fixtures;
import com.week4.concert.domain.queue.ongoing.OngoingAppender;
import com.week4.concert.domain.queue.ongoing.OngoingReader;
import com.week4.concert.domain.queue.ongoing.OngoingRemover;
import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import com.week4.concert.domain.queue.waiting.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class WaitingServiceTest {

    private WaitingReader waitingReader;
    private WaitingAppender waitingAppender;
    private WaitingRemover waitingRemover;
    private WaitingService waitingService;

    @BeforeEach
    void setUp() {
        waitingReader = mock(WaitingReader.class);
        waitingAppender = mock(WaitingAppender.class);
        waitingRemover = mock(WaitingRemover.class);
        waitingService = new WaitingService(waitingReader, waitingAppender, waitingRemover);
    }

    @Test
    @DisplayName("Waiting service check 성공")
    void check() {
        //given
        Long userId = 1L;
        Waiting testUser = Fixtures.waiting(userId);
        given(waitingService.check(userId)).willReturn(testUser);
        //when
        Waiting result = waitingReader.check(userId);
        //then
        assertThat(result.userId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("Waiting service 가장 우선순위 N명 아이디 select")
    void selectTopN() {
        //given
        List<Waiting> testList = new ArrayList<>();
        testList.add(new Waiting(1L,1L, LocalDateTime.now(),"waiting"));
        testList.add(new Waiting(2L,2L, LocalDateTime.now(),"waiting"));
        given(waitingReader.selectTopN(2)).willReturn(testList);
        //when
        List<Waiting> result = waitingService.selectTopN(2);
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).userId()).isEqualTo(1);
        assertThat(result.get(1).userId()).isEqualTo(2);
    }

    @Test
    @DisplayName("Waiting service save 성공")
    void insert() {
        //given
        Long userId = 1000L;
        //when
        waitingService.insert(userId);
        //then
    }

    @Test
    @DisplayName("Waiting service updateDone 성공")
    void remove() {
        //given
        Long userId = 1000L;
        //when
        waitingService.updateDone(userId);
        //then
    }
}
