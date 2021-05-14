package com.myproject.moods.distribute.shardjdbc;

import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-14
 */
public class ShardingDome {

    @Resource
    private DataSource  dataSource;

    String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.user_id=? AND o.order_id=?";

    Connection conn;

    {
        try {
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);{
                preparedStatement.setInt(1, 10);
                try {
                    preparedStatement.setInt(2, 1001);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try (ResultSet rs = preparedStatement.executeQuery()) {
            while(rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getInt(2));
            }
        }
    }

} catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}