package com.example.demo.ReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {



    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
        Lock lock=readWriteLock.writeLock();
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
