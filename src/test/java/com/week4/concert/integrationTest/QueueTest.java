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
