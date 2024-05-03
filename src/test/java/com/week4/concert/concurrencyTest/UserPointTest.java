package com.week4.concert.concurrencyTest;

import com.week4.concert.base.lockHandler.LockHandler;
import com.week4.concert.domain.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserPointTest {

    @Autowired
    private UserService userService;

    @Autowired
    private LockHandler lockHandler;

    @BeforeEach
    void resetRedis() {
        lockHandler.reset();
    }

    @Test
    @DisplayName("사용/충전 동시 요청시 순차적으로 처리")
    public void handle_sequentially_charge_and_use() throws InterruptedException {

        //given : data.sql 로 userId 1 100000포인트 소유

        // when : 2000포인트 충전, 1000포인트 사용 동시에 100회 요청
        int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(120);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    userService.chargePoint(1L, 2000);
                    userService.usePoint(1L, 1000);
                } catch (Exception e) {

                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        //then
        assertThat(userService.getPoint(1L)).isEqualTo(100000 + (2000 * 100) - (1000 * 100));
    }
}
