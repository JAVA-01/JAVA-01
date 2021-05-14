package com.myproject.moods.distribute.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-04-25
 */
@Component
public  class RedisDistributeCounter {
    @Autowired
    public  StringRedisTemplate redisTemplate;

    public long counterIncr(String key,long incr ){
        redisTemplate.opsForValue().setIfAbsent(key,"0");

         return redisTemplate.opsForValue().increment(key,incr);
    }
    public long counterDesc(String key,long desc){
        return  redisTemplate.opsForValue().decrement(key,desc);
    }


}
