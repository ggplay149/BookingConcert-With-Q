package com.week4.concert.integrationTest;

import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import com.week4.concert.domain.queue.waiting.WaitingService;
import com.week4.concert.application.QueueUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QueueTest {

    @Autowired
    private OngoingSerivce ongoingSerivce;
    @Autowired
    private WaitingService waitingService;
    @Autowired
    private QueueUseCase queueUseCase;


    @Test
    @Transactional
    @DisplayName("Ongoing 48명일 때 waiting 테이블 최우선 2명추가 / 이후 추가된 2명 waiting 테이블에서 soft delete(done) 처리 확인")
    void queueUpdate() {

        //given
        for (long i = 1; i <= 48; i++) {
            ongoingSerivce.insert(i);
        }
        for (long i = 49; i <= 49 + 10; i++) {
            waitingService.insert(i);
        }

        //when
        queueUseCase.queueUpdate();

        //then
        assertThat(ongoingSerivce.countOngoing()).isEqualTo(50);
        assertThat(ongoingSerivce.check(50L).status()).isEqualTo("Ongoing");
        assertThat(waitingService.selectTopN(2).get(0).userId()).isEqualTo(51);
    }

    @Test
    @DisplayName(" waiting or ongoing 테이블에 둘다 없으면 대기열입장 가능")
    void insertQueue1() {
        //given
        //when
        String result = queueUseCase.insertQueue(888L);
        //then
        assertThat(result).isEqualTo("Entry");
    }

    @Test
    @DisplayName("waiting테이블 상태에 waiting 상태로 존재하면 추가 대기열입장 불가능")
    void insertQueue2() {
        //given
        waitingService.insert(777L);
        //when
        String result = queueUseCase.insertQueue(777L);
        //then
        assertThat(result).isEqualTo("Blocked");
    }

    @Test
    @DisplayName("ongoing테이블 상태에 ongoing 상태로 존재하면 추가 대기열입장 불가능")
    void insertQueue3() {
        //given
        ongoingSerivce.insert(999L);
        //when
        String result = queueUseCase.insertQueue(999L);
        //then
        assertThat(result).isEqualTo("Blocked");
    }

}
