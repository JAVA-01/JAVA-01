package com.example.demo.Wait_notify;

/**
 * @author 孟祥迎
 *@date 2021-02-06
 **/
public class Main {

    private static Object lock=new Object();

    public static void main(String[] args) throws InterruptedException {

        Notify notify=new Notify(lock);
        notify.start();
        Thread.sleep(1000);
        synchronized (lock){
            System.out.println("处于等待方法执行中");
        }
        System.out.println("主线程结束");

    }
}
