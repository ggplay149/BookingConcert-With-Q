package com.week4.concert.domain.queue.ongoing;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OngoingSerivce {

    private final OngoingReader ongoingReader;
    private final OngoingAppender ongoingAppender;
    private final OngoingRemover ongoingRemover;


    public OngoingSerivce(OngoingReader ongoingReader, OngoingAppender ongoingAppender, OngoingRemover ongoingRemover) {
        this.ongoingReader = ongoingReader;
        this.ongoingAppender = ongoingAppender;
        this.ongoingRemover = ongoingRemover;
    }

    public Ongoing check(Long userId) {
        return ongoingReader.check(userId);
    }

    public String checkbeforeInsert(Long userId){
        try {
            check(userId);
            return "Exist";
        }catch (Exception e) {
            return "Not Exist";
        }
    }

    public Integer countOngoing() {
        return ongoingReader.countOngoing();
    }

    public void insert(Long userId){
        ongoingAppender.save(userId);
    }

    public void updateDone(Long userId){
        ongoingRemover.updateDone(userId);
    }
}
