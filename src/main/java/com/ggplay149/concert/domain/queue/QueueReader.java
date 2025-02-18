package com.ggplay149.concert.domain.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class QueueReader {

    private final QueueRepository queueRepository;

    public Boolean checkUserStatus(Long userId, String key) {
        return queueRepository.checkUserStatus(userId, key);
    }

    public Long countActiveUsers() {
        return queueRepository.countActiveUsers();
    }

    public String[] getNewActiveUsers(Long topN) { return queueRepository.getNewActiveUsers(topN); }

    public Set<ZSetOperations.TypedTuple<String>> getCurrentActiveUsers(){ return queueRepository.getUserExpiryTime(); }

}
