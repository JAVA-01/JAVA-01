package com.example.demo.JDBCDB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author DELL
 */
public class NativeUtils {
    private final static String url = "jdbc:mysql://localhost/pan?user=root&password=123456&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
    private final static String drive = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;


    static {

        try{
            Class.forName(drive);
            connection = DriverManager.getConnection(url);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {

        }

    }
        public static Connection getConnection(){

        return  connection;
        }

}

