package com.example.demo.lockSupport;

import java.util.concurrent.locks.LockSupport;

public class Threadz extends Thread{
    Thread thread;

    public Threadz(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        this.method();
        LockSupport.unpark(thread);
    }

    void method(){
        System.out.println("线程方法执行完成");
    }
}
