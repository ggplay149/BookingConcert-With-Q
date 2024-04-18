package com.week4.concert.UnitTest.domain.queue.waiting;

import com.week4.concert.Fixtures;
import com.week4.concert.domain.queue.ongoing.Ongoing;
import com.week4.concert.domain.queue.waiting.Waiting;
import com.week4.concert.domain.queue.waiting.WaitingReader;
import com.week4.concert.domain.queue.waiting.WaitingRepository;
import com.week4.concert.storage.queue.waiting.WaitingEntity;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class WaitingReaderTest {

    private WaitingRepository waitingRepository;
    private WaitingReader waitingReader;

    @BeforeEach
    void setUp() {
        waitingRepository = mock(WaitingRepository.class);
        waitingReader = new WaitingReader(waitingRepository);
    }

    @Test
    @DisplayName("Waiting check 성공")
    void check() {
        //given
        Long userId = 1L;
        Waiting testUser = Fixtures.waiting(userId);
        given(waitingRepository.check(userId)).willReturn(testUser);
        //when
        Waiting result = waitingReader.check(userId);
        //then
        assertThat(result.userId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("Waiting 가장 우선순위 N명 아이디 select")
    void selectTopN() {
        //given
        List<Waiting> testList = new ArrayList<>();
        testList.add(new Waiting(1L,1L, LocalDateTime.now(),"waiting"));
        testList.add(new Waiting(2L,2L, LocalDateTime.now(),"waiting"));
        given(waitingRepository.selectTopN(2)).willReturn(testList);
        //when
        List<Waiting> result = waitingReader.selectTopN(2);
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).userId()).isEqualTo(1);
        assertThat(result.get(1).userId()).isEqualTo(2);
    }
}
