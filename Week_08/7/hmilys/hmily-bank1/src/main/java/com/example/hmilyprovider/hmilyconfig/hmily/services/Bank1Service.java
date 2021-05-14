package com.example.hmilyprovider.hmilyconfig.hmily.services;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-13
 */
public interface Bank1Service {

    /**
     * 此处不做真实处理
     * @param AccountNO
     * @param count
     */
    public  void updateAcount(String AccountNO,double count);

    /**
     *  此方法被调用时是confirm方法，进行此用户表的真实增加金额
     * @param AccountNO
     * @param count
     */
    public void confirm(String AccountNO,double count);

    /**
     * 默认confirm方法一定成果故而此处不做处理
     * @param AccountNO
     * @param count
     */
    public void cancel(String AccountNO,double count);

}
