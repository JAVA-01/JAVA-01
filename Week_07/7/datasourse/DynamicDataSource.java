package com.myproject.moods.distribute.datasourse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-08
 * 通知spring用key当前的数据源呢，查阅资料可知，spring提供一个接口，名为AbstractRoutingDataSource的抽象类
 * 我们只需要重写determineCurrentLookupKey方法就可以，这个方法看名字就知道，就是返回当前线程的数据源的key，那我们只需要从我们刚刚的数据源上下文中取出我们的key即可
 *
 */

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DynamicDataSourceContextHolder.getDataSourceType();
        logger.info("当前数据源是：{}", dataSourceName);
        return DynamicDataSourceContextHolder.getDataSourceType();
    }


}
