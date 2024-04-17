package com.week4.concert.UnitTest.storage.queue.ongoing;

import com.week4.concert.storage.queue.ongoing.OngoingCoreRepository;
import com.week4.concert.storage.queue.ongoing.OngoingJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OngoingCoreRepositoryTest {

    private OngoingCoreRepository ongoingCoreRepository;
    private OngoingJpaRepository ongoingJpaRepository;

    @BeforeEach
    void setup() {
        ongoingJpaRepository = mock(OngoingJpaRepository.class);
        ongoingCoreRepository = new OngoingCoreRepository(ongoingJpaRepository);
    }

    @Test
    @DisplayName("Ongoing insert 성공")
    void Success_Ongoing_insert() {
        //given
        Long userId = 1000L;
        //when
        ongoingCoreRepository.save(userId);
        //then
    }

    @Test
    @DisplayName("Ongoing updateDone 성공")
    void Success_Ongoing_remove() {
        //given
        Long userId = 1000L;
        //when
        ongoingCoreRepository.updateDone(userId);
        //then
    }

    @Test
    @DisplayName("Ongoing check 후 없으면 에러")
    void when_not_found_user_in_ongoing_then_error() {
        //given
        Long userId = 1000L;
        given(ongoingJpaRepository.check(userId)).willReturn(Optional.empty());
        //when
        Exception result = assertThrows(EntityNotFoundException.class, () -> ongoingCoreRepository.check(userId));
        //then
        assertThat(result.getMessage()).isEqualTo("활성화된 유저가 아닙니다.");
    }

    @Test
    @DisplayName("Ongoing 유저수 조회")
    void success_Ongoing_count() {
        //given
        given(ongoingJpaRepository.countOngoing()).willReturn(5);
        //when
        Integer result = ongoingCoreRepository.countOngoing();
        //then
        assertThat(result).isEqualTo(5);
    }
}
