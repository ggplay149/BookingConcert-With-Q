package com.week4.concert.unitTest.api.useCase;

import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import com.week4.concert.domain.queue.waiting.Waiting;
import com.week4.concert.domain.queue.waiting.WaitingService;
import com.week4.concert.application.QueueUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class QueueUseCaseTest {

    private WaitingService waitingService;
    private OngoingSerivce ongoingSerivce;
    private QueueUseCase queueUseCase;

    @BeforeEach
    void setUp() {
        waitingService = mock(WaitingService.class);
        ongoingSerivce = mock(OngoingSerivce.class);
        queueUseCase = new QueueUseCase(waitingService,ongoingSerivce);
    }

    @Test
    @DisplayName("부족한 Ongoing 숫자 추가 업데이트")
    void queueUpdate(){

        //given
        given(ongoingSerivce.countOngoing()).willReturn(48);

        List<Waiting> testWaitingList = new ArrayList<>();
        testWaitingList.add(new Waiting(1L,1l, LocalDateTime.now(),"waiting"));
        testWaitingList.add(new Waiting(2L,2l, LocalDateTime.now(),"waiting"));
        given(waitingService.selectTopN(50-48)).willReturn(testWaitingList);

        //when
        queueUseCase.queueUpdate();
        //then
    }

}
