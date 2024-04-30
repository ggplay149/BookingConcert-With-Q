package com.week4.concert.IntegrationTest;

import com.week4.concert.application.ReservationUseCase;
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
public class ReservationTest {

    @Autowired
    private ReservationUseCase reservationUseCase;

    @Test
    @DisplayName("좌석예약 동시성 이슈 방지")
    public void when_reserve_concurrent_issue() throws InterruptedException {

        int threadCount = 20;
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

        assert result.get(0).equals("5분간 좌석이 임시 배정되었습니다. 결제완료시 최종 확정됩니다.");
        assert result.get(1).equals("이미 예약된 좌석입니다.");
        assert result.get(2).equals("이미 예약된 좌석입니다.");
    }


    @Test
    @DisplayName("해당콘서트 최대인원 조회후 예약된 좌석번호 빼주고 예약가능 리스트 리턴")
    void selectAvailableSeat() {

        //given : data.sql으로 1,4,7 좌석 예약, capacity : 50
        //when
        List<Integer> result = reservationUseCase.selectAvailableSeat("20241112", "MuseConcert");
        //then
        assertThat(result.size()).isEqualTo(46);
        assertThat(result.get(0)).isEqualTo(2);
        assertThat(result.get(2)).isEqualTo(5);
    }

    @Test
    @DisplayName("중복 좌석으로 인한 예약 실패")
    void reserve1() {
        //given
        reservationUseCase.reserve("20241112", "MuseConcert", 15L, 49);
        //when
        Exception result = assertThrows(RuntimeException.class,
                ()->reservationUseCase.reserve("20241112", "MuseConcert", 15L, 49));
        //then
        assertThat(result.getMessage()).isEqualTo("이미 예약된 좌석입니다.");
    }

    @Test
    @DisplayName("예약 성공(임시배정)")
    void reserve2() {
        //given
        //when
        String result = reservationUseCase.reserve("20241112", "MuseConcert", 15L, 25);

        //then
        assertThat(result).isEqualTo("5분간 좌석이 임시 배정되었습니다. 결제완료시 최종 확정됩니다.");
    }


}
