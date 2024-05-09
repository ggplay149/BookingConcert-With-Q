package com.week4.concert.base.scheduler;

import com.week4.concert.domain.queue.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenScheduler {

    private final QueueService queueService;

    @Scheduled(fixedRate = 10000)
    public void insertNewActiveUsers() { queueService.insertNewActiveUsers(); }

    @Scheduled(fixedRate = 60000)
    public void removeExpiredActiveUsers() { queueService.removeExpiredActiveUsers(); }

}
