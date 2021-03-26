package com.example.demo.mysqlInsert;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;



@Component
@Slf4j
public class Insert {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource datasource;


    List<Order> orders ;
    List<Object[]> ordersValues;

    final static int total=1000000;
    final  private int [] types={Types.BIGINT,Types.BIGINT,Types.SMALLINT,Types.BIGINT,Types.VARCHAR};
    String sql ="insert into orders values(?,?,?,?,?)";
    /**
     * 初始化方法
     * 对类属性进行初始化，方便业务调用
     *
     * */
    @PostConstruct
    private void init(){
        orders =new ArrayList<>(total);
        ordersValues=new ArrayList<>(total);
        Map json =new HashMap();
        json.put("name","张三");
        json.put("call","1589623458");
        json.put("address","张家古楼");

        for (int i = 0; i <total ; i++) {
            /**
             *
             * 静态调用builder（）build方法，创建实例，填充进入集合             * */
            orders.add(Order.builder().goods_id(RandomUtil.randomLong()).order_id(RandomUtil.randomLong()).price_now(RandomUtil.randomInt())
                    .user_id(RandomUtil.randomLong()).json(json).build());
            ordersValues.add(new Object[]{RandomUtil.randomLong(),RandomUtil.randomLong(),RandomUtil.randomInt(1000),RandomUtil.randomLong(),json.toString()});
        }

    }

    /**
     * 使用jbcdTemplate进行插入，在源码中可以看到，这种批处理也是用的preparestatement，进行sql预编译
     *1687601mills
     * */
    public  void templateInsert () throws SQLException {
        log.info("使用template进行插入");
        long time_pre =System.currentTimeMillis();
        jdbcTemplate.batchUpdate(sql,ordersValues,types);
        long time = System.currentTimeMillis()-time_pre;
        log.info("jdbcTemplate十线程并发处理耗时{}",time);
    }

    public  void  myinsert(){
        List<List<Object[]>> splits=CollectionUtil.split(ordersValues,total/10);

        try {
            parallel(this::insert,splits,10
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将一个lambada作作为接口实现类传入parallel ，十线程处理
     *
     * 302933mill
     * */
    public void templateInsertParallel() throws InterruptedException {
        log.info("template十线程并行插入开始");
        List<List<Object[]>> splits=CollectionUtil.split(ordersValues,total/10);
        parallel(
                (args)-> {jdbcTemplate.batchUpdate(sql,(List<Object[]>) args,types);},splits,10
        );

    }

    /**
     * 此方法不关心具体业务如何实现，提供一个十线程并行执行的方法，执行具体业务的方法在参数处传入可以用lambada表达式
     * param consume 消化一个参数，不返回
     *
     * args 分割好的参数列表
     *
     * size 并发线程
     * */
    public <T> void parallel(Consumer<List<T>>  consumer, List<List<T>> args,int size) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(size);
        CountDownLatch countDownLatch=new CountDownLatch(size);
        long time_pre=System.currentTimeMillis();
        for ( List<T> object: args
             ) {
            executor.submit(
                    ()->{
                        consumer.accept(object);
                        countDownLatch.countDown();
                    }
            );
        }
        countDownLatch.await();
        log.info("template十线程并行插入耗时{}mill",System.currentTimeMillis()-time_pre);
    }



    public  void  insert(List<Object[]> ordersValues )  {
        Connection connection = null;
        try {
            connection = datasource.getConnection();
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            int m=0;
            int n=0;
            for (Object[] args:ordersValues
            ) {
                int k=0;
                for (Object v:
                        args) {
                    preparedStatement.setObject(k+1,v,types[k++]);
                }
                preparedStatement.addBatch();
                m++;
                if(m>=total/100){
                    preparedStatement.executeBatch();
                m=0;
                preparedStatement.clearBatch();
                log.info("第{}批处理完成",++n);}
            }
            preparedStatement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
