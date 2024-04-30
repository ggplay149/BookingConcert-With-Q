package com.week4.concert.concurrencyTest;

import com.week4.concert.domain.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class UserPointTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("연속충전(따닥) 방지")
    public void testPreventConsecutiveRecharge() throws InterruptedException {

        userService.chargePoint(1L,1000);
        Thread.sleep(10000);
        userService.chargePoint(1L,1000);

        System.out.println(">>>>> : " + userService.getPoint(1L));

    }
}
