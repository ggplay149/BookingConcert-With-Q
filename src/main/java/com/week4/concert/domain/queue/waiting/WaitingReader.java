package com.week4.concert.domain.queue.waiting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WaitingReader {

    private final WaitingRepository waitingRepository;

    public Waiting check(Long userId) {
        return waitingRepository.check(userId);
    }

    public List<Waiting> selectTopN(int topN) {
        return waitingRepository.selectTopN(topN);
    }
}
