package com.week4.concert.domain.queue.waiting;

import jakarta.persistence.EntityNotFoundException;
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

    public Waiting check(Long userId) {return waitingReader.check(userId);}

    public String checkBeforeInsert(Long userId){
        try{
            check(userId);
            return "Exist";
        }catch (Exception e){
            return "Not Exist";
        }
    }


    public List<Waiting> selectTopN(int topN) {return waitingReader.selectTopN(topN);}

    public void insert(Long userId) {
        waitingAppender.save(userId);
    }

    public void updateDone(Long id) {
        waitingRemover.updateDone(id);
    }
}
