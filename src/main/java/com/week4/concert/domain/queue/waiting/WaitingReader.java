package com.week4.concert.domain.queue.waiting;

import com.week4.concert.domain.queue.ongoing.Ongoing;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WaitingReader {

    private final WaitingRepository waitingRepository;

    public WaitingReader(WaitingRepository waitingRepository) {
        this.waitingRepository = waitingRepository;
    }

    public Waiting check(Long userId) {
        return waitingRepository.check(userId);
    }

    public List<Waiting> selectTopN(int topN){
        return waitingRepository.selectTopN(topN);
    }
}
