package com.week4.concert.domain.queue.waiting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaitingService {

    private final WaitingReader waitingReader;
    private final WaitingAppender waitingAppender;
    private final WaitingRemover waitingRemover;

    public Waiting check(Long userId) { return waitingReader.check(userId); }

    public void insert(Long userId) {
        waitingAppender.save(userId);
    }

    public void updateDone(Long id) {
        waitingRemover.updateDone(id);
    }

    public List<Waiting> selectTopN(int topN) { return waitingReader.selectTopN(topN); }

    public String checkBeforeInsert(Long userId) {
        try {
            check(userId);
            return "Exist";
        } catch (Exception e) {
            return "Not Exist";
        }
    }
}
