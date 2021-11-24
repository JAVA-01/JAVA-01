package com.example.demo.lock1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {



    public static void main(String[] args) throws InterruptedException {
        Lock lock=new ReentrantLock();
        Threadz threadz=new Threadz(lock);
        threadz.start();
        System.out.println("主方法执行中");
        Thread.sleep(1000);
        lock.lock();
        try{
            System.out.println("主方法执行中");
        }catch (Exception e){}
        finally {
            lock.unlock();
        }
        System.out.println("主线程结束");


    }



}
