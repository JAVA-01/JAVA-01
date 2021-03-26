package com.example.demo.aop;

import java.lang.reflect.Proxy;
import java.util.Scanner;

/**
 * @author DELL
 */
public class Main {

    public static void main(String[] args) {

        /**
         * JDK动态代理
         * */
        SomeoneImp someoneImp=new SomeoneImp();
        /**
         *handler 代理被代理对象，并对其做一些加强
         *
         * * */
        JdkProxyHandler jdkProxyHandler=new JdkProxyHandler(someoneImp);
        /**
         * Proxy.newProxyInstance其实是实现了接口的一个实现类，所以可以强转为接口，而不可以强转为自定义的实现类
         * **/
        Someone proxy=(Someone)jdkProxyHandler.getProxy();
        proxy.eat();
        proxy.sleep();

        /**
         * CGLIB动态代理
         *因为这里的代理类是通过类对象创建子类实现的，所以可以用被代理类接收
         * */
        Cglib cglib =new Cglib();
         SomeoneImp Proxy  = (SomeoneImp) cglib.createProxyObj(SomeoneImp.class);
        proxy.sleep();
        proxy.eat();



    }

}
