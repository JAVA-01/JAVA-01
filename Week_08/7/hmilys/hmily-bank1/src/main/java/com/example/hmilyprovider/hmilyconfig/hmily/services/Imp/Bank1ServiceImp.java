package com.example.hmilyprovider.hmilyconfig.hmily.services.Imp;

import com.example.hmilyprovider.hmilyconfig.hmily.dao.AccountInfoDao;
import com.example.hmilyprovider.hmilyconfig.hmily.services.Bank1Service;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.core.context.HmilyTransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-13
 */
@Service
@Slf4j
public class Bank1ServiceImp implements Bank1Service {
    @Autowired
    AccountInfoDao accountInfoDao;

    @Override
    @HmilyTCC(confirmMethod = "confirm",cancelMethod = "cancel")
    public void updateAcount(String AccountNO, double count) {
        HmilyTransactionContext hmilyTransactionContext =new HmilyTransactionContext();
        log.info(this.getClass().toString()+"try方法执行"+hmilyTransactionContext.getTransId());
    }

    @Override
    public void confirm(String AccountNO, double count) {
      HmilyTransactionContext hmilyTransactionContext =new HmilyTransactionContext();
      long transId =hmilyTransactionContext.getTransId();
        if(accountInfoDao.isExistConfirm(String.valueOf(transId))>0){
            log.info("已经执行过了");
            return;
        }
        if(accountInfoDao.isExistAccountNo(AccountNO)<=0){
            log.info("AccountNo不存在");
        }
        if(accountInfoDao.addAccountBalance(AccountNO,count)<=0){
            throw new RuntimeException("confirm失败");
        }


    }

    @Override
    public void cancel(String AccountNO, double count) {
        HmilyTransactionContext hmilyTransactionContext =new HmilyTransactionContext();
        log.info(this.getClass().toString()+"try方法执行"+hmilyTransactionContext.getTransId());
    }

}
