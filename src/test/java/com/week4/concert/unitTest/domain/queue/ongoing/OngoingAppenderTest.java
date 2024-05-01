package com.week4.concert.unitTest.domain.queue.ongoing;

import com.week4.concert.domain.queue.ongoing.OngoingAppender;
import com.week4.concert.domain.queue.ongoing.OngoingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class OngoingAppenderTest {

    private OngoingRepository ongoingRepository;
    private OngoingAppender ongoingAppender;

    @BeforeEach
    void setUp() {
        ongoingRepository = mock(OngoingRepository.class);
        ongoingAppender = new OngoingAppender(ongoingRepository);
    }

    @Test
    @DisplayName("Ongoing Appender insert 성공")
    void save() {
        //given
        Long userId = 1000L;
        //when
        ongoingAppender.save(userId);
        //then
    }
}
