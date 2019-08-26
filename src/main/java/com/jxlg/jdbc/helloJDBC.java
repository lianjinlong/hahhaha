package com.jxlg.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.Properties;

public class helloJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册驱动
        //注册驱动的四种方式
        //1.1 使用Class.forName("驱动类全名的字符串表示");
       // String driver="com.mysql.jdbc.Driver";
     //   Class.forName(driver);
//        //1.2   使用drivermanager调用先关方法注册驱动
//        //在程序中自行创建相关Driver类对象
//        //不推荐使用这种方式
//        Driver driver = new  Driver();
//        DriverManager.registerDriver(driver);
        //1.3  使用System类注册驱动
        //使用System类调用setproperty方法，传递两参数
        //相当于给JAVA系统配置了一个环节变量
//         String driver="com.mysql.jdbc.Driver";
//         System.setProperty("jdbc.drivers",driver);

         //1.4 在JDBC2.0版本中不需要显示的注册驱动
         //也就是说在程序中完全可以不用写注册驱动的代码




        //2.获取连接对象 connection 对象     //获取连接有三种方式
        //
        //cmd>mydql -uroot -p -h127.0.0.1 -p5726

        //获取连接时还需要：URL，用户名，密码
        //2.1 单独提供URL，用户名，密码
//        String url="jdbc:mysql://127.0.0.1:5726/briup";
//        String user="root";
//        String password="root";
//
//        Connection conn = DriverManager.getConnection(url, user, password);
//
//        //打印对象
//        System.out.println(conn);
//        //2.2 只提供URL
//
//        //其实是用户名和密码以参数的形式直接集成在URL中
//        String url="jdbc:mysql://127.0.0.1:5726/briup?user=root&password=root";
//        Connection conn = DriverManager.getConnection(url);
//        //打印connection对象
//        System.out.println(conn);

        //2.3 提供简单的url，将用户名和密码封装至property对象中
        String url="jdbc:mysql://127.0.0.1:5726/briup?useSSL=true";
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","root");
        Connection conn = DriverManager.getConnection(url, properties);
       // 打印对象
        System.out.println(conn);

        //3.获取statement对象
        Statement stat = conn.createStatement();


        //4.执行SQL语句
        //执行Sql语句方法有三种
//        stat.executeQuery(sql);     方法的返回值是ResultSet,一般该方法用于执行DQL
//        stat.execute(sql);         方法的返回值是布尔类型，返回值代表改sql语句是否执行失败，返回值代表改SQL语句的执行是否出错，一般用于执行DDL，DCL
//        stat.executeUpdate(sql);    改方法的返回值是int类型，返回值代表改SQL语句在执行完成之后影响了数据库表中的多少条记录，一般用于执行DML
        String sql="show databases";
        ResultSet rs = stat.executeQuery(sql);


        //5.处理结果集
        while(rs.next()){
            String dbname = rs.getString(1);
            System.out.println(dbname);
        }

        //6.关闭资源
        rs.close();
        stat.close();
        conn.close();

    }
}
