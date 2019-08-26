package com.jxlg.jdbc;
/*
工具类，静态，单例模式
 */

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    private String driver;
    private String url;
    private String user;
    private String password;

    private static JdbcUtil util;


    //单例模式
    //1.构造器私有化
    private JdbcUtil(){
        try {
            //初始化数据库连接信息
            //1.创建Properties对象
            Properties prop = new Properties();

            //2.使用Properties对象加载配置文件

            InputStream is = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
            prop.load(is);

            //3.通过Properties对象获取相关参数的值
            this.driver=prop.getProperty("db.driver");
            this.url=prop.getProperty("db.url");
            this.user=prop.getProperty("db.user");
            this.password=prop.getProperty("db.password");

            //注册驱动
            Class.forName(this.driver);

        }catch (Exception ex){
            System.out.println(ex);
            throw new RuntimeException("构造器加载失败");
        }
    }

    //2.提供公共的访问接口
    public static JdbcUtil getInstance(){
        if(util==null) {
            synchronized (JdbcUtil.class) {
                if (util == null) {
                    util = new JdbcUtil();
                }
            }
        }
        return util;
    }


    public Connection getConnection() throws Exception{
        Connection con = DriverManager.getConnection(this.url, this.user, this.password);
        return con;
    }

    public Statement getStatement() throws Exception{
        Statement stat = this.getConnection().createStatement();
        return stat;
    }

    public PreparedStatement getPStat(String sql) throws Exception{
        return this.getConnection().prepareStatement(sql);
    }

    public void close(ResultSet rs,Statement stat,Connection con){
        if(rs!=null){
            try {
                rs.close();
            }catch (Exception ex){}
        }
        if(stat!=null){
            try{
                stat.close();
            }catch (Exception ex){}
        }
        if(con!=null){
            try{
                con.close();
            }catch (Exception ex){}
        }
    }

}
