package com.example.hmilyprovider.hmilyconfig.dubbo.imp;

import com.example.hmilyprovider.hmilyconfig.dubbo.interfaces.Greet;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-12
 */
@Service
public class GreetImp implements Greet {
    @Override
    public String sayhi(String name) {
        return "hi"+ name;
    }
}
