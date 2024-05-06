package com.week4.concert.domain.reservation;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ReservationExpirationHandler {

    private RedisTemplate<String, String> redisTemplate;

    public ReservationExpirationHandler(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Boolean setExpirationTime(String key, String value, long ttl) {
        return redisTemplate
                .opsForValue()
                .setIfAbsent(key, value, Duration.ofSeconds(ttl));
    }

    public Boolean removeExpirationTime(String key) {
        return redisTemplate.delete(key);
    }

    public String validateExpiration(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
