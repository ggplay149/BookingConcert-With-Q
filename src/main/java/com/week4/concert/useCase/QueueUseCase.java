package com.week4.concert.useCase;

import com.week4.concert.domain.queue.ongoing.Ongoing;
import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import com.week4.concert.domain.queue.waiting.Waiting;
import com.week4.concert.domain.queue.waiting.WaitingService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class QueueUseCase {

    private final WaitingService waitingService;
    private final OngoingSerivce ongoingSerivce;

    public QueueUseCase(WaitingService waitingService, OngoingSerivce ongoingSerivce) {
        this.waitingService = waitingService;
        this.ongoingSerivce = ongoingSerivce;
    }

     public void queueUpdate() {

        int ongoingCount = ongoingSerivce.countOngoing();

        if (ongoingCount < 50) {

            List<Waiting> nextUserList = waitingService.selectTopN(50 - ongoingCount);

            for (Waiting waitingUser : nextUserList) {

                ongoingSerivce.insert(waitingUser.userId());

                waitingService.updateDone(waitingUser.id()); // soft delete ) status => 'Done'

            }
        }
    }

        public String insertQueue(Long userId) {

        String waitingTable = waitingService.checkbeforeInsert(userId);
        String ongoingTable = ongoingSerivce.checkbeforeInsert(userId);

        if (waitingTable.equals("Not Exist") && ongoingTable.equals("Not Exist")) {
            waitingService.insert(userId);
            return "Entry";
        }

        return "Blocked";

    }
}
