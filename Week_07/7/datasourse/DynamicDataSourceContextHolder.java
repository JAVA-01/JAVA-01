package com.myproject.moods.distribute.datasourse;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-08
 * 需要新建一个数据源上下文，用户记录当前线程使用的数据源的key是什么，以及记录所有注册成功的数据源的key的集合。对于线程级别的私有变量，我们首先ThreadLocal来实现。
 */
import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static List<String> dataSourceIds = new ArrayList<>();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    /**
     * 判断指定DataSrouce当前是否存在
     *
     * @param dataSourceId
     * @return
     * @author SHANHY
     * @create  2016年1月24日
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}
