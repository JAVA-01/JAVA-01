package com.example.demo.Wait_notify;
/**
 * @author 孟祥迎
 *@date 2021-02-06
 **/
public class Threadz extends Thread {

    Object lock;

    public Notify(Object lock) {
        this.lock = lock;
    }



    @Override
    public void run() {
        synchronized (lock){
            this.method();
        }


    }

    public  void method(){

        System.out.println("方法执行完毕");

    }


}
