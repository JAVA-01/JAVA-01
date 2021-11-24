package com.example.hmilyconsumer.hmily.dao.services;

import com.google.inject.internal.cglib.proxy.$Callback;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.stereotype.Service;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-13
 */
@Service
public interface Bank1Service {
    /**
     * 资源准备，此处为扣除相应金额，冻结在参数中
     * 另外调用另外一个服务
     * 如果调用失败本地会回滚
     * @param AccountNo
     * @param count
     */
    public void  addAcount(String AccountNo,Double count);

    /**
     * 此处没有实际操作
     * @param AccountNo
     * @param count
     */
    public  void confirm(String AccountNo,Double count);

    /**
     * 对try的回滚
     * 将冻结在参数中的数据返回
     *
     * @param AccountNo
     * @param count
     */
    public  void cancel(String AccountNo,Double count);


}
