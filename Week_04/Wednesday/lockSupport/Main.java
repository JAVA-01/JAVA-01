package com.example.demo.lockSupport;

import java.util.concurrent.locks.LockSupport;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("主方法开始执行");

        Threadz threadz=new Threadz(Thread.currentThread());
        threadz.start();
        Thread.sleep(1000);
        LockSupport.park("park");
        System.out.println("主线程结束");




    }
}
