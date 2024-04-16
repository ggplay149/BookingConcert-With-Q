package com.week4.concert.domain.queue.ongoing;

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
        ongoingRemover.remove(userId);
        //then
    }
}
