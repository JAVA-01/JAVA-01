package com.example.demo.Wait_notify;

/**
 * @author 孟祥迎
 *@date 2021-02-06
 *@description wait/notify和wait的区别只在于 wait和notify取代了sleep
 **/
public class Wait {

    private static Object lock=new Object();

    public static void main(String[] args) throws InterruptedException {

        Notify notify=new Notify(lock);
        notify.start();

        synchronized (lock){
            System.out.println("处于等待方法执行中");
            lock.wait();
        }
        System.out.println("主线程结束");

    }
}
