package com.example.demo.JDBCDB;



import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author DELL
 */
public class JdbcTest {

    private static DbDao dbDao =new DbDao();

    public static void main(String[] args) throws SQLException {
        Connection connection =NativeUtils.getConnection();
        System.out.println(connection);
//        add(6,"sdas");
        delete();

    }




    public  static  void add(int id, String username) throws SQLException {
        Users users=new Users();
        users.setId(id);
        users.setUsername(username);
        users.setDatabaseurl("sad");
        users.setPassword("da");
        dbDao.addUser(users);
    }
    public  static  void delete() throws SQLException {
        dbDao.delete();
    }


}
