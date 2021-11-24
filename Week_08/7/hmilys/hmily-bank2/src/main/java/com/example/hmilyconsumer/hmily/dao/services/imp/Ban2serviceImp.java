package com.example.hmilyconsumer.hmily.dao.services.imp;

import com.example.hmilyconsumer.hmily.dao.dao.AccountInfoDao;
import com.example.hmilyconsumer.hmily.dao.services.Bank1Service;
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
public class Ban2serviceImp implements Bank1Service {

    @Autowired
    AccountInfoDao accountInfoDao;

    @HmilyTCC(confirmMethod = "confirm",cancelMethod="cancel")
    @Override
    public void addAcount(String AccountNo,Double count) {
    HmilyTransactionContext hmilyTransactionContext =new HmilyTransactionContext();
    long transId = hmilyTransactionContext.getTransId();

    log.info("try方法开始执行"+transId);
    if(accountInfoDao.isExistAccountNo(AccountNo)<=0){
        throw new RuntimeException("accountNo 不存在");
    }
        /**
         *
         *幂等判断和 防倒挂处理
          */
        if(accountInfoDao.isExistTry(String.valueOf(transId))>0){
            log.info("已经 执行过 try 操作");
            return;
        }
        if(accountInfoDao.isExistCancel(String.valueOf(transId))>0||accountInfoDao.isExistConfirm(String.valueOf(transId))>0){
            log.info("confirm操作和cancel操作已经执行过");
            return;
        }

        /**
         * 模拟转账，此处是账号减少金额
         */

        if(accountInfoDao.updateAccountBalance(AccountNo,count)==0){
         throw new RuntimeException("尝试冻结资金失败" + transId);
        }
        /**
         * 远程调用
         *
         *
         */

        if(count==4){
            throw  new RuntimeException("人为异常，查看执行情况");
        }
        log.info("try执行结束");
    }

    @Override
    public void confirm(String AccountNo,Double count) {
        HmilyTransactionContext hmilyTransactionContext =new HmilyTransactionContext();
        log.info("执行confirm方法" +hmilyTransactionContext.getTransId());
    }


    @Override
    public void cancel(String AccountNo,Double count) {
        HmilyTransactionContext hmilyTransactionContext =new HmilyTransactionContext();
        long transId=hmilyTransactionContext.getTransId();
        /**
         * 重新加回去，冻结在参数里面
         */
        accountInfoDao.addAccountBalance(AccountNo,count);
        log.info("执行cancel 方法" +transId);
    }
}
