package com.week4.concert.unitTest.domain.user;

import com.week4.concert.base.lockHandler.LockHandler;
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
    private LockHandler lockHandler;

    @BeforeEach
    void setUp() {
        userReader = mock(UserReader.class);
        userPointCharger = mock(UserPointCharger.class);
        lockHandler = mock(LockHandler.class);

        userService = new UserService(userReader, userPointCharger,lockHandler);
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
}
