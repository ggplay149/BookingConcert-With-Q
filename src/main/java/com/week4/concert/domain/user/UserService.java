package com.week4.concert.domain.user;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReader userReader;
    private final UserPointCharger userPointCharger;
    private final RedissonClient redissonClient;

    @Transactional(readOnly = true)
    public Integer getPoint(Long userId) {
        return userReader.getPoint(userId);
    }

    @Transactional(readOnly = true)
    public void checkPoint(Integer concertPrice, Long userId) {
        if (getPoint(userId) - concertPrice < 0)
            throw new RuntimeException("잔액이 부족합니다.");
    }

    @Transactional
    public void chargePoint(Long userId, Integer point){
        RLock lock = redissonClient.getLock("user" + userId);
        try {
            lock.tryLock(3, 1, TimeUnit.SECONDS);
            Integer currentPoint = getPoint(userId);
            userPointCharger.chargePoint(userId, currentPoint + point);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public void usePoint(Long userId, Integer point){
        chargePoint(userId, point * -1);
    }
}
