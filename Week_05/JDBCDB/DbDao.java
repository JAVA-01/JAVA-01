package com.example.demo.JDBCDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author DELL
 */
public class DbDao {

        public  int addUser (Users users) throws SQLException {
            Connection connection= NativeUtils.getConnection();
            String sql ="INSERT INTO users(id,username) VALUES(?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);

            preparedStatement.setInt(1,users.getId());
            preparedStatement.setString(2,users.getUsername());

            boolean res =preparedStatement.execute();

            preparedStatement.close();

            return (res==true) ? 1:0;
        }
        public  void delete () throws SQLException {
            Connection connection =NativeUtils.getConnection();
            String sql ="delete  from users where id =?";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setInt(1,1);
            preparedStatement.addBatch();
            preparedStatement.setInt(1,3);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();

        }




}
