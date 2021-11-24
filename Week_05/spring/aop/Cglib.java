package com.example.demo.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author DELL
 */
public class Cglib  implements MethodInterceptor  {
    /**
     * 通过增强的方式获取代理类
     * 代理类是被代理类的子类
     * 通过类对象的创建，不需要实例化被代理对象
     *
     * 代理类会重写父类的所有方法，在其中调用方法拦截器的interceptor方法
     * 没有 被代理对象的 实例对象 故不能根据实例对象调用相应方法而是在代理类中 写了一些以cglib开头的方法调用父类的方法
         *
     * */
    public  Object createProxyObj (Class<?> clazz){
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return  enhancer.create();
    }

    /**
     *param:  代理类调用时传入
     * o是调用方 ——代理类本身this
     * method 代理类重写的和父类重名的方法，就是此方法调用的MethodInterceptor的intercept方法
     * Object 入参数
     * methodProxy 代理类的cglib开头的方法，其调用了父类的方法super.XX
     **/
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        methodProxy.invokeSuper(o,objects);
        return null;
    }
}
