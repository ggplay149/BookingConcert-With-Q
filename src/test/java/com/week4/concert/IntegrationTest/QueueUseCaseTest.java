package com.week4.concert.IntegrationTest;

import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import com.week4.concert.domain.queue.waiting.WaitingService;
import com.week4.concert.useCase.QueueUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QueueUseCaseTest {

    @Autowired
    private OngoingSerivce ongoingSerivce;
    @Autowired
    private WaitingService waitingService;
    @Autowired
    private QueueUseCase queueUseCase;


    @Test
    @DisplayName("Ongoing 48명일 때 Wating 2명추가")
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
        assertThat(waitingService.selectTopN(1).get(0).userId()).isEqualTo(51);

    }
}
