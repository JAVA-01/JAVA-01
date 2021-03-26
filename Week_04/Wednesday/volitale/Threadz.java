package com.example.demo.volitale;

import java.util.concurrent.locks.Lock;

public class Threadz extends Thread {

    volatile  Flag flag ;

    public Threadz(Flag flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        this.method();
        flag.aBoolean=true;
    }

    void method(){
        System.out.println("子方法执行完成");
    }
}
