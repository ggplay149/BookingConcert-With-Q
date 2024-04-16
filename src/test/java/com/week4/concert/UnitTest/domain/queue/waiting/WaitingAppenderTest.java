package com.week4.concert.UnitTest.domain.queue.waiting;

import com.week4.concert.domain.queue.waiting.WaitingAppender;
import com.week4.concert.domain.queue.waiting.WaitingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class WaitingAppenderTest {

    private WaitingRepository waitingRepository;
    private WaitingAppender waitingAppender;

    @BeforeEach
    void setUp() {
        waitingRepository = mock(WaitingRepository.class);
        waitingAppender = new WaitingAppender(waitingRepository);
    }

    @Test
    @DisplayName("Waiting Appender insert 성공")
    void save() {
        //given
        Long userId = 1000L;
        //when
        waitingAppender.save(userId);
        //then
    }
}
