package com.example.hmilyprovider.hmilyconfig.dubbo;

import com.example.hmilyprovider.hmilyconfig.dubbo.interfaces.Greet;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-12
 */
public class Consumer {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "47.98.192.167");

    public static void main(String[] args) {
        ReferenceConfig<Greet> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        reference.setInterface(Greet.class);
        Greet service = reference.get();
        String message = service.sayhi("dubbo");
        System.out.println(message);
    }
}
