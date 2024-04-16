package com.week4.concert.domain.queue.waiting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class WaitingRemoverTest {

    private WaitingRepository waitingRepository;
    private WaitingRemover waitingRemover;

    @BeforeEach
    void setUp() {
        waitingRepository = mock(WaitingRepository.class);
        waitingRemover = new WaitingRemover(waitingRepository);
    }

    @Test
    @DisplayName("Waiting Appender remvoe 성공")
    void save() {
        //given
        Long userId = 1000L;
        //when
        waitingRemover.remove(userId);
        //then
    }
}
