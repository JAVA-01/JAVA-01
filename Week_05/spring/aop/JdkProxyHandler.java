package com.example.demo.aop;

import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.InvokeHandler;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author DELL
 */
public class JdkProxyHandler implements InvocationHandler {

    private  Object target;

    public JdkProxyHandler(Object target) {
        this.target = target;
    }

    /**
     * handler类被Proxy类代理，由Proxy类调用handler的invoke放，传入Method和args
    * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        method.invoke(target,args);
        System.out.println("after");

        return null;
    }
    /**
     * 此方法一般在工厂类中，用于获取Proxy代理类
     *
     *
     * */
    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }




}
