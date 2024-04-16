package com.week4.concert.storage.queue.ongoing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("Ongoing remove 성공")
    void Success_Ongoing_remove() {
        //given
        Long userId = 1000L;
        //when
        ongoingCoreRepository.deleteById(userId);
        //then
    }
}
