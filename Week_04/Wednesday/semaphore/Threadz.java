package com.example.demo.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Threadz extends Thread {
    Object object=new Object();
    Semaphore lock=new Semaphore(1);

    public Threadz(Semaphore lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.acquireUninterruptibly();
        try {
            System.out.println("子线程执行");
            this.method();
        }catch (Exception e){}
        finally {
            lock.release();
        }
    }

    void method(){
        System.out.println("子方法执行完成");
    }
}
