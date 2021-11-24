package com.example.demo.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Threadz extends Thread{

    CountDownLatch countDownLatch;

    public Threadz(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        this.method();
        countDownLatch.countDown();

    }

    void method(){
        System.out.println("方法执行结束");
    }

}
