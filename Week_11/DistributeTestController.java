package com.myproject.moods.distribute;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-04-25
 */
@RestController
public class DistributeTestController {
    RedisMessageListenerContainer redisMessageListenerContainer=new RedisMessageListenerContainer();

    @PostMapping("/test")
    public void test(@ApiParam(name = "id") String id){

        /**
         * 并发操作
         *
         * 其中有 stringRedisTemplate.convertAndSend(key, "this is a redis callback");方法执行
         *
         */

        CompletableFuture completableFuture =new CompletableFuture();
        ChannelTopic channelTopic =new ChannelTopic(id);
        MyMessageListener myMessageListener =new MyMessageListener(completableFuture);
        redisMessageListenerContainer.addMessageListener(myMessageListener,channelTopic);

        try {
            String message =  (String) completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        finally {
            if(myMessageListener!=null){
                redisMessageListenerContainer.removeMessageListener(myMessageListener);
            }

        }


    }
}
