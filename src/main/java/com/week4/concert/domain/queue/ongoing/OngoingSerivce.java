package com.week4.concert.domain.queue.ongoing;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OngoingSerivce {

    private final OngoingReader ongoingReader;
    private final OngoingAppender ongoingAppender;
    private final OngoingRemover ongoingRemover;


    public Ongoing check(Long userId) { return ongoingReader.check(userId); }

    public Integer countOngoing() {
        return ongoingReader.countOngoing();
    }

    public void insert(Long userId){
        ongoingAppender.save(userId);
    }

    public void updateDone(Long userId){
        ongoingRemover.updateDone(userId);
    }

    public String checkBeforeInsert(Long userId){
        try {
            check(userId);
            return "Exist";
        }catch (Exception e) {
            return "Not Exist";
        }
    }
}
