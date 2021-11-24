package com.example.demo.thread_join;

public class Threadz extends Thread{


    @Override
    public void run() {
        this.method();
    }
    void  method(){
        System.out.println("方法执行完成");
    }
}
