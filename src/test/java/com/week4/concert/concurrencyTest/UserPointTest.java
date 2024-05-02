package com.week4.concert.concurrencyTest;

import com.week4.concert.base.lockHandler.LockHandler;
import com.week4.concert.domain.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserPointTest {

    @Autowired
    private UserService userService;

    @Autowired
    private LockHandler lockHandler;

    @Test
    @DisplayName("연속충전(따닥) 방지")
    public void prevent_consecutive_recharge() throws InterruptedException {

        lockHandler.unlock("user" + 1L);
        userService.chargePoint(1L, 1000);
        Exception result = assertThrows(RuntimeException.class, () -> userService.chargePoint(1L, 1000));
        assertThat(result.getMessage()).isEqualTo("3초내 연속으로 진행할수 없습니다.");


    }
}
