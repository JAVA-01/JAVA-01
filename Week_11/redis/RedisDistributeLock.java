package com.myproject.moods.distribute.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-04-
 * 分布式锁
 */
@Component
public class RedisDistributeLock {
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 不断重试，尽可能解决问题提出的地方
     * @param lock_id
     * @param milliSecond
     * @return
     */
    public boolean getLock(String lock_id,long milliSecond ){
        boolean success=false;
        do {
            success = redisTemplate.opsForValue().setIfAbsent(lock_id, "lock", milliSecond, TimeUnit.MICROSECONDS);
        }while (!success);
        return success;
    }


    public boolean releaseLock(String lock_id){
       boolean success=false;
       success=redisTemplate.delete(lock_id);
       return success;
    }



}
