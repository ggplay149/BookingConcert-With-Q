package com.ggplay149.concert.concurrencyTest;

import com.ggplay149.concert.application.ReservationUseCase;
import com.ggplay149.concert.base.lockHandler.LockHandler;
import org.junit.jupiter.api.BeforeEach;
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

@SpringBootTest
public class ReservationTest {

    @Autowired
    private ReservationUseCase reservationUseCase;

    @Autowired
    private LockHandler lockHandler;

    @BeforeEach
    void resetRedis() {
        lockHandler.reset();
    }


    @Test
    @DisplayName("같은 좌석 5명 동시예약시 1명 성공 4명 실패")
    public void concurrency_issue_control_when_reserve() throws InterruptedException {

        //given

        // when : 5명 같은좌석 동시 예약 요청
        int threadCount = 5;
        Long idCount = 1L;
        final List<String> result = new ArrayList<>();
        final ExecutorService executorService = Executors.newFixedThreadPool(30);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            idCount++;
            final Long testId = idCount;
            executorService.submit(() -> {
                try {
                    result.add(reservationUseCase.createTemporaryReservation("20240504", "PsyConcert", testId, 5));
                } catch (Exception e) {
                    result.add(e.getMessage());
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        //then : 1명만 임시배정 성공
        assertThat(result.get(0)).isEqualTo("[ 예약번호 : 20240504.2.5 ] 5분간 좌석이 임시 배정되었습니다. 결제완료시 최종 확정됩니다.");
        assertThat(result.get(1)).isEqualTo("임시 배정된 좌석입니다. 다른 좌석을 선택해주세요.");
        assertThat(result.get(2)).isEqualTo("임시 배정된 좌석입니다. 다른 좌석을 선택해주세요.");
        assertThat(result.get(3)).isEqualTo("임시 배정된 좌석입니다. 다른 좌석을 선택해주세요.");
        assertThat(result.get(4)).isEqualTo("임시 배정된 좌석입니다. 다른 좌석을 선택해주세요.");
    }

}
