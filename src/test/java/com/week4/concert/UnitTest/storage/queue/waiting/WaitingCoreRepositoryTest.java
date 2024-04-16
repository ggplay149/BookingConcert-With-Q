package com.week4.concert.UnitTest.storage.queue.waiting;

import com.week4.concert.domain.queue.waiting.Waiting;
import com.week4.concert.storage.queue.ongoing.OngoingCoreRepository;
import com.week4.concert.storage.queue.ongoing.OngoingJpaRepository;
import com.week4.concert.storage.queue.waiting.WaitingCoreRepository;
import com.week4.concert.storage.queue.waiting.WaitingEntity;
import com.week4.concert.storage.queue.waiting.WaitingJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class WaitingCoreRepositoryTest {

    private WaitingCoreRepository waitingCoreRepository;
    private WaitingJpaRepository waitingJpaRepository;

    @BeforeEach
    void setup() {
        waitingJpaRepository = mock(WaitingJpaRepository.class);
        waitingCoreRepository = new WaitingCoreRepository(waitingJpaRepository);
    }

    @Test
    @DisplayName("Waiting insert 성공")
    void success_waiting_insert() {
        //given
        Long userId = 1000L;
        //when
        waitingCoreRepository.save(userId);
        //then
    }

    @Test
    @DisplayName("Waiting remove 성공")
    void success_waiting_remove() {
        //given
        Long userId = 1000L;
        //when
        waitingCoreRepository.deleteById(userId);
        //then
    }
    @Test
    @DisplayName("대기열 최우선 두명 아이디 조회")
    void select_top2() {
        //given
        List<WaitingEntity> testList = new ArrayList<>();
        testList.add(new WaitingEntity(1L,1L, LocalDateTime.now()));
        testList.add(new WaitingEntity(2L,2L, LocalDateTime.now()));
        given(waitingJpaRepository.selectTopN(PageRequest.of(0, 2))).willReturn(testList);
        //when
        List<Waiting> result = waitingCoreRepository.selectTopN(2);
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).userId()).isEqualTo(1);
        assertThat(result.get(1).userId()).isEqualTo(2);
    }
}
