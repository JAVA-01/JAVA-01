package com.example.demo.thread_join;

import com.example.demo.thread_join.Threadz;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {



    public static void main(String[] args) throws InterruptedException {
        Lock lock=new ReentrantLock();
        Threadz threadz=new Threadz();
        System.out.println("主方法执行中");
        threadz.start();
        threadz.join();
        System.out.println("主线程结束");


    }



}
