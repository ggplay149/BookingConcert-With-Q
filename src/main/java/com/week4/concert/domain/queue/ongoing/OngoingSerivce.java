package com.week4.concert.domain.queue.ongoing;

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

    public Integer countOngoing() {
        return ongoingReader.countOngoing();
    }

    public void insert(Long userId){
        ongoingAppender.save(userId);
    }

    public void remove(Long userId){
        ongoingRemover.remove(userId);
    }
}
