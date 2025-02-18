package com.ggplay149.concert.base.lockHandler;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class LockHandler {

    private RedisTemplate<String, String> redisTemplate;
    private RedissonClient redissonClient;

    public LockHandler(RedisTemplate<String, String> redisTemplate, RedissonClient redissonClient) {
        this.redisTemplate = redisTemplate;
        this.redissonClient = redissonClient;
    }

    public void lock(String key, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(key);

        try {
            boolean available = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
            if (!available) return;
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }


    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void reset() {
        Set<String> keys = redisTemplate.keys("*");
        if (keys != null) redisTemplate.delete(keys);
    }
}
