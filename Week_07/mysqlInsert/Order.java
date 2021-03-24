package com.example.demo.mysqlInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
@Data
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**  这里同时需要有参构造和无参构造，有参构造提供给builder ，由于component又需要无参构成给spring创建容器使用*/
public class Order {

    private long user_id;

    private long goods_id;

    private long order_id;

    private int  price_now;

    private Map json;

}
