package com.week4.concert.UnitTest.domain.queue.ongoing;

import com.week4.concert.Fixtures;
import com.week4.concert.domain.queue.ongoing.Ongoing;
import com.week4.concert.domain.queue.ongoing.OngoingReader;
import com.week4.concert.domain.queue.ongoing.OngoingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OngoingReaderTest {

    private OngoingRepository ongoingRepository;
    private OngoingReader ongoingReader;

    @BeforeEach
    void setUp() {
        ongoingRepository = mock(OngoingRepository.class);
        ongoingReader = new OngoingReader(ongoingRepository);
    }

    @Test
    @DisplayName("Ongoing check 성공")
    void check() {
        //given
        Long userId = 1L;
        Ongoing testUser = Fixtures.ongoing(userId);
        given(ongoingRepository.check(userId)).willReturn(testUser);
        //when
        Ongoing result = ongoingReader.check(userId);
        //then
        assertThat(result.userId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("Ongoing 유저수 조회")
    void countOngoing() {
        //given
        given(ongoingRepository.countOngoing()).willReturn(5);
        //when
        Integer result = ongoingReader.countOngoing();
        //then
        assertThat(result).isEqualTo(5);
    }
}
