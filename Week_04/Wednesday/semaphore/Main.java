package com.example.demo.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {



    public static void main(String[] args) throws InterruptedException {
        Semaphore lock=new Semaphore(1);
        Threadz threadz=new Threadz(lock);
        threadz.start();
        System.out.println("主方法执行中");
        Thread.sleep(1000);
        lock.acquireUninterruptibly();
        try{
            System.out.println("主方法执行中");
        }catch (Exception e){}
        finally {
            lock.release();
        }
        System.out.println("主线程结束");


    }



}
