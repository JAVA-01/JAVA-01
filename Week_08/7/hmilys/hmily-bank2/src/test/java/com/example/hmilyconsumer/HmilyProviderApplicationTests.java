package com.example.hmilyconsumer;

import com.example.hmilyconsumer.hmily.dao.dao.AccountInfoDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HmilyProviderApplicationTests {
    @Autowired
    AccountInfoDao accountInfoDao;


    @Test
    void contextLoads() {

    accountInfoDao.addAccountBalance("2",1.0);


    }

}
