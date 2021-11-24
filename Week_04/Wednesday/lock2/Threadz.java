package com.example.demo.lock2;

import java.util.concurrent.locks.Lock;

public class Threadz extends Thread {
    Object object=new Object();
    Lock lock;

    public Threadz(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if(lock.tryLock()){
        try {
            System.out.println("子线程执行");
            this.method();
        }catch (Exception e){}
        finally {
            lock.unlock();
        }}
    }

    void method(){
        System.out.println("子方法执行完成");
    }
}
