package com.week4.concert.UnitTest.domain.user;

import com.week4.concert.domain.user.UserPointCharger;
import com.week4.concert.domain.user.UserReader;
import com.week4.concert.domain.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class userServiceTest {

    private UserService userService;
    private UserReader userReader;
    private UserPointCharger userPointCharger;

    @BeforeEach
    void setUp() {
        userReader = mock(UserReader.class);
        userPointCharger = mock(UserPointCharger.class);
        userService = new UserService(userReader, userPointCharger);
    }

    @Test
    @DisplayName("유저포인터 조회 성공")
    void getPoint() {
        //given
        given(userService.getPoint(any())).willReturn(1000);
        //when
        Integer result = userReader.getPoint(1L);
        //then
        assert result == 1000;
    }

    @Test
    @DisplayName("유저포인터 충전 성공")
    void chargePoint() {
        //given
        //when
        userService.chargePoint(1L, 1000);
        //then
    }
}
