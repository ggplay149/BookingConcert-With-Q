package com.week4.concert.base.scheduler;

import com.week4.concert.application.QueueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenScheduler {

    private final QueueUseCase queueUseCase;

    //10초간격
    @Scheduled(fixedRate = 10000)
    public void tokenUpdate(){
        queueUseCase.queueUpdate();
    }
}
