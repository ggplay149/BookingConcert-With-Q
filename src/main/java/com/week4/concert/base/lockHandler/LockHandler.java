package com.week4.concert.base.lockHandler;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;

@Component
public class LockHandler {

    private RedisTemplate<String,String> redisTemplate;


    public LockHandler(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Boolean lock(String key,String value, long ttl){
        return redisTemplate
                .opsForValue()
                .setIfAbsent(key,value, Duration.ofSeconds(ttl));
    }

    public Boolean unlock(String key){
        return redisTemplate.delete(key);
    }

    public String  getValue(String key) { return redisTemplate.opsForValue().get(key); }

    public void reset(){
        Set<String> keys = redisTemplate.keys("*");
        if (keys != null)  redisTemplate.delete(keys);
    }
}
