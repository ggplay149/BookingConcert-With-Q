package com.week4.concert.domain.queue.waiting;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingService {

    private final WaitingReader waitingReader;
    private final WaitingAppender waitingAppender;
    private final WaitingRemover waitingRemover;

    public WaitingService(WaitingReader waitingReader, WaitingAppender waitingAppender, WaitingRemover waitingRemover) {
        this.waitingReader = waitingReader;
        this.waitingAppender = waitingAppender;
        this.waitingRemover = waitingRemover;
    }

    public List<Waiting> selectTopN(int topN){
        return waitingReader.selectTopN(topN);
    }

    public void insert(Long userId) {
        waitingAppender.save(userId);
    }

    public void remove(Long userId) {
        waitingRemover.remove(userId);
    }
}
