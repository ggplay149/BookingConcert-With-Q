package com.week4.concert.unitTest.domain.queue.ongoing;

import com.week4.concert.domain.queue.ongoing.OngoingRemover;
import com.week4.concert.domain.queue.ongoing.OngoingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class OngoingRemoverTest {

    private OngoingRepository ongoingRepository;
    private OngoingRemover ongoingRemover;

    @BeforeEach
    void setUp() {
        ongoingRepository = mock(OngoingRepository.class);
        ongoingRemover = new OngoingRemover(ongoingRepository);
    }

    @Test
    @DisplayName("Ongoing Appender delete 성공")
    void remove() {
        //given
        Long userId = 1000L;
        //when
        ongoingRemover.updateDone(userId);
        //then
    }
}
