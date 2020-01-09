package com.travelone.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

    private static DataSource ds;

     /**
     * 加载的代码写在静态代码块中
     */
    static {
        try {
            Properties info = new Properties();
            //加载类路径下，即src目录下的druid.properties这个文件
            InputStream input = JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            info.load(input);

            //读取属性文件创建连接池
            ds = DruidDataSourceFactory.createDataSource(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到数据源
     */
    public static DataSource getDataSource() {
        return ds;
    }

    /**
     * 得到连接对象
     */
    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放资源
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }

}
