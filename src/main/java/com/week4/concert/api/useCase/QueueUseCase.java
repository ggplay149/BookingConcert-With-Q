package com.week4.concert.api.useCase;

import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import com.week4.concert.domain.queue.waiting.Waiting;
import com.week4.concert.domain.queue.waiting.WaitingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QueueUseCase {

    private final WaitingService waitingService;
    private final OngoingSerivce ongoingSerivce;


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

        String waitingTable = waitingService.checkBeforeInsert(userId);
        String ongoingTable = ongoingSerivce.checkBeforeInsert(userId);

        String resultMessage = "Blocked";

        if (waitingTable.equals("Not Exist") && ongoingTable.equals("Not Exist")) {
            waitingService.insert(userId);
            resultMessage ="Entry";
        }

        queueUpdate();
        return resultMessage;
    }

    public String checkQueue(Long userId) {
        ongoingSerivce.check(userId);
        return "활성화 된 유저입니다.";
    }
}
