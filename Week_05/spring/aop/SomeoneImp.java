package com.example.demo.aop;

/**
 * @author DELL
 */
public class SomeoneImp  implements Someone {
    @Override
    public void sleep() {
        System.out.println("就知道睡觉");
    }

    @Override
    public void eat() {
        System.out.println("就知道吃饭");
    }
}
