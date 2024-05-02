package com.week4.concert.concurrencyTest;

import com.week4.concert.base.lockHandler.LockHandler;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserPointTest {

    @Autowired
    private UserService userService;

    @Autowired
    private LockHandler lockHandler;

    @Test
    @DisplayName("사용/충전 동시 요청")
    public void prevent_consecutive_recharge() throws InterruptedException {

        //given

        // when : 5명 같은좌석 동시 예약 요청
        int threadCount = 6;

        final ExecutorService executorService = Executors.newFixedThreadPool(30);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    userService.chargePoint(1L,2000);
                    userService.usePoint(1L,1000);
                } catch (Exception e) {

                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        //then
        assertThat(userService.getPoint(1L)).isEqualTo(104000);


    }
}
