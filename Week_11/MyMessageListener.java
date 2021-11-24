package com.myproject.moods.distribute;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.concurrent.CompletableFuture;


/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-04-25
 */
public class MyMessageListener implements MessageListener {
    CompletableFuture completableFuture;

    public MyMessageListener(CompletableFuture completableFuture) {
        this.completableFuture = completableFuture;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        completableFuture.complete(message.toString());
    }
}
