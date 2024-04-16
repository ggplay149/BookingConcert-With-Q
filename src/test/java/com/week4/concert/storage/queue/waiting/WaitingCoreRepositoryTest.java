package com.week4.concert.storage.queue.waiting;

import com.week4.concert.storage.queue.ongoing.OngoingCoreRepository;
import com.week4.concert.storage.queue.ongoing.OngoingJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    void Success_Waiting_insert() {
        //given
        Long userId = 1000L;
        //when
        waitingCoreRepository.save(userId);
        //then
    }

    @Test
    @DisplayName("Waiting remove 성공")
    void Success_Waiting_remove() {
        //given
        Long userId = 1000L;
        //when
        waitingCoreRepository.deleteById(userId);
        //then
    }
}
