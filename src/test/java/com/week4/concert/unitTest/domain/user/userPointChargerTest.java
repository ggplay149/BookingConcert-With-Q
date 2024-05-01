package com.week4.concert.unitTest.domain.user;

import com.week4.concert.domain.user.UserPointCharger;
import com.week4.concert.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class userPointChargerTest {

    private UserRepository userRepository;
    private UserPointCharger userPointCharger;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userPointCharger = new UserPointCharger(userRepository);
    }

    @Test
    @DisplayName("유저포인터 충전 성공")
    void chargePoint() {
        //given
        //when
        userPointCharger.chargePoint(1L, 1000);
        //then
    }
}
