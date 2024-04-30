package com.week4.concert.base.lockHandler;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class LockHandler {

    private RedisTemplate<String,String> redisTemplate;


    public LockHandler(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Boolean lock(Long key,long ttl){
        System.out.println("핸들러들어옴");
        return redisTemplate
                .opsForValue()
                .setIfAbsent(generateKey(key),"lock", Duration.ofSeconds(30L));
    }

    public Boolean unlock(Long key){
        return redisTemplate.delete(generateKey(key));
    }

    public String generateKey(Long key){
        return key.toString();
    }
}
