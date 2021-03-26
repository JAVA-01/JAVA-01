package com.example.demo.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(1);
        System.out.println("主方法执行中");
        Threadz threadz=new Threadz(countDownLatch);
        threadz.start();
        countDownLatch.await();
        System.out.println("主方法结束");


    }
}
