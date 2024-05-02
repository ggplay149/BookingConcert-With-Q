package com.week4.concert.concurrencyTest;

import com.week4.concert.application.ReservationUseCase;
import com.week4.concert.base.lockHandler.LockHandler;
import com.week4.concert.domain.user.UserPointCharger;
import com.week4.concert.domain.user.UserRepository;
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
import static org.mockito.Mockito.mock;

@SpringBootTest
public class ReservationTest {

    @Autowired
    private ReservationUseCase reservationUseCase;

    @Autowired
    private LockHandler lockHandler;

    @BeforeEach
    void resetRedis(){
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
                    result.add(reservationUseCase.reserve("20240504","PsyConcert",testId,5));
                } catch (Exception e) {
                    result.add(e.getMessage());
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        //then
        assertThat(result.get(0)).isEqualTo("[ 예약번호 : 20240504.2.5 ] 5분간 좌석이 임시 배정되었습니다. 결제완료시 최종 확정됩니다.");
        assertThat(result.get(1)).isEqualTo("임시 배정된 좌석입니다. 다른 좌석을 선택해주세요.");
        assertThat(result.get(2)).isEqualTo("임시 배정된 좌석입니다. 다른 좌석을 선택해주세요.");
        assertThat(result.get(3)).isEqualTo("임시 배정된 좌석입니다. 다른 좌석을 선택해주세요.");
        assertThat(result.get(4)).isEqualTo("임시 배정된 좌석입니다. 다른 좌석을 선택해주세요.");
    }

    @Test
    @DisplayName("임시배정 시간 초과후 다음 예약 요청 성공")
    public void success_reserve_after_unlock_time_out() throws InterruptedException {

        //given :먼저 예약
        reservationUseCase.reserve("20240504","PsyConcert",2L,27);

        //when : 유효시간 지나고 ( = unlock), 다시 예약
        lockHandler.unlock("20240504.2.27");
        String result = reservationUseCase.reserve("20240504","PsyConcert",1L,27);

        //then : 예약 성공 메세지
        assertThat(result).isEqualTo("[ 예약번호 : 20240504.2.27 ] 5분간 좌석이 임시 배정되었습니다. 결제완료시 최종 확정됩니다.");

    }
}
