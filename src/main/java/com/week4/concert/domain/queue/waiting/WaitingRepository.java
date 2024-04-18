package com.week4.concert.domain.queue.waiting;

import java.util.List;

public interface WaitingRepository {

    void save(Long userId);

    void updateDone(Long id);

    List<Waiting> selectTopN(int topN);

    Waiting check(Long userId);
}
