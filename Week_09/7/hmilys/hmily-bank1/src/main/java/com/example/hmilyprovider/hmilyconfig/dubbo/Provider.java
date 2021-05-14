package com.example.hmilyprovider.hmilyconfig.dubbo;

import com.example.hmilyprovider.hmilyconfig.dubbo.imp.GreetImp;
import com.example.hmilyprovider.hmilyconfig.dubbo.interfaces.Greet;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.concurrent.CountDownLatch;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-12
 */
public class Provider {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "47.98.192.167");

    public static void main(String[] args) throws Exception {
        ServiceConfig<Greet> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        service.setInterface(Greet.class);
        service.setRef(new GreetImp());
        service.export();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }

}
