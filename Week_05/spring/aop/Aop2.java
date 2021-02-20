//package com.example.demo.spring.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Before;
//
//public class Aop2 {
//
//
//    @Before(value="annotation(com.example.demo.annotation)")
//    void before(){
//        System.out.println("前置通知");
//    }
//
//
//    @After(value = "annotation(com.example.demo.annotation)")
//    void after(){
//        System.out.println("后置通知");
//    }
//
//    //这个参数是动态传入的
//
//    @Around(value = "annotation(com.example.demo.annotation)")
//    void around(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("执行前");
//        joinPoint.proceed();
//        System.out.println("执行后");
//    }
//
//}
