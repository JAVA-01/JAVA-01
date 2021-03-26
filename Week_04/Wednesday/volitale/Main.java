package com.example.demo.volitale;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        Flag flag=new Flag();
        flag.aBoolean=false;
        System.out.println("主方法执行中");
        Threadz threadz=new Threadz(flag);
        threadz.start();
        while(!flag.aBoolean){}
        System.out.println("主方法执行完成");
    }



}
