package com.week4.concert.scheduler;

import com.week4.concert.useCase.QueueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenScheduler {

    private final QueueUseCase queueUseCase;

//    @Scheduled(fixedRate = 10000)
//    public void tokenUpdate(){
//        queueUseCase.queueUpdate();
//    }
}
