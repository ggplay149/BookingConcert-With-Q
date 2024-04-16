package com.week4.concert.domain.queue.ongoing;

import com.week4.concert.domain.user.User;

public interface OngoingRepository {

    void save(Long userId);

    void deleteById(Long userId);

}
