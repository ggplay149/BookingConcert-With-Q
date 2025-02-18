package com.ggplay149.concert.unitTest.domain.user;

import com.ggplay149.concert.domain.user.UserReader;
import com.ggplay149.concert.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class userReaderTest {

    private UserRepository userRepository;
    private UserReader userReader;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userReader = new UserReader(userRepository);
    }


    @Test
    @DisplayName("유저포인터 조회 성공")
    void getPoint() {
        //given
        given(userRepository.getPoint(any())).willReturn(1000);
        //when
        Integer result = userReader.getPoint(1L);
        //then
        assert result == 1000;
    }

}
