package com.week4.concert.UnitTest.infrastructure.user;
import com.week4.concert.infrastructure.user.UserCoreRepository;
import com.week4.concert.infrastructure.user.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class userCoreRepositoryTest {

    private UserCoreRepository userCoreRepository;
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    void setUp() {
        userJpaRepository = mock(UserJpaRepository.class);
        userCoreRepository = new UserCoreRepository(userJpaRepository);
    }

    @Test
    @DisplayName("없는 아이디 포인트 조회")
    void when_not_found_user_then_error() {
        //given
        given(userJpaRepository.findById(any())).willReturn(Optional.empty());
        // when
        Exception e = assertThrows(EntityNotFoundException.class, () -> userCoreRepository.getPoint(1L));
        //then
        assertThat(e.getMessage()).isEqualTo("존재 하지 않는 아이디 입니다.");
    }

    @Test
    @DisplayName("충전 성공")
    void when_success_charge_point() {
        //given
        // when
        userCoreRepository.chargePoint(1L,1000);
        //then

    }
}
