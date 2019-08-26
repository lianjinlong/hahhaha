package com.jxlg.jdbc;

import java.sql.*;

/**
 *  用于JDBC的增删改查的操作
 *  C:create 新增
 *  R:retrieve，获取
 *  U:update ，更新
 *  D:Delete，删除
 */
public class CRUD {

    private String driver;
    private String url;
    private String user;
    private String password;
    private  JdbcUtil util;
    private  Statement Stat;



    private Connection conn;
    public CRUD() throws Exception {
//        //成员变量初始化
//        this.driver="com.mysql.jdbc.Driver";
//        this.url="jdbc:mysql://127.0.0.1:5726/briup?useSSL=true";
//        this.user="root";
//        this.password="root";
//
//        //注册驱动
//        Class.forName(driver);
//
//        this.conn= DriverManager.getConnection(url,user,password);
util=JdbcUtil.getInstance();
this.conn=util.getConnection();
    }
//    public void createDatabase(String dbname) throws SQLException {
//        Statement stat = this.conn.createStatement();
//        String sql="create database "+dbname;
//        boolean execute = stat.execute(sql);
//        System.out.println(execute);
//
//    }

    //Iterable 集合的顶层接口 ，可迭代性
    //常见的集合： list set   collection 单列集合    set元素不可重复  list可重复  map比set出现更早
    //  Map  双列集合
    // Vector  :可变长数组，向量    单列集合的前身
    // HashTable ：
    // collections :工具类，里面所有的方法都是 静态
    // Arrays： 工具类，静态
    // JdbcUtil : 工具类， 静态
    //单例模式：构造器进行私有化
//    public  void createTable(String tbName) throws SQLException {
//        Statement stat = this.conn.createStatement();
//        String sql="create table dep."+tbName+"("+
//                "id int primary key auto_increment,"+"name varchar(20)"+
//                ")";
//        boolean execute = stat.execute(sql);
//        System.out.println(execute);
//    }

//    public void insert() throws SQLException {
////        Statement statement = this.conn.createStatement();
////        String sql="insert into dep.ljl(name) values('张三'),('李四'),('王五')";
////        int i = statement.executeUpdate(sql);
////        System.out.println(i);
////    }

// public void  delete(){
//        try{
//  this.conn.setAutoCommit(false);
//
//  String sql="delete from helloworld.students where id=?";
//        this.util.getPstat(sql);
//
//            pstat.setInt(1,1);
//
//            pstat.excuteupdate();
//            System.out.println(i);
//
//
//        }catch (Exception e){
//        e.printStackTrace();
//        try{
//            this.conn.rollback();
//        }catch (SQLException ex){
//            ex.printStackTrace();
//        }
//
//        }
// }
   //public  void updata(){}


//    public void select() throws SQLException {
//        Statement stat = this.conn.createStatement();
//        String sql ="select * from dep.ljl";
//        ResultSet rs = stat.executeQuery(sql);
//
//        while (rs.next()){
//            int id = rs.getInt(1);
//            String name= rs.getString(2);
//            System.out.println(id+"......"+name);
//        }
//
//    }


//    public void select(String nameString) throws SQLException {
//        Statement stat = this.conn.createStatement();
//        String sql ="select * from dep.ljl where name='"+nameString+"'";
//        ResultSet rs = stat.executeQuery(sql);
//
//        while (rs.next()){
//            int id = rs.getInt(1);
//            String name= rs.getString(2);
//            System.out.println(id+"......"+name);
//        }
//
//    }

//    public void select2(String nameString) throws SQLException {
//
//        String sql ="select * from dep.ljl where name=?";
//        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
//        preparedStatement.setString(1,nameString);
//        ResultSet rs = preparedStatement.executeQuery();
//
//
//        while (rs.next()){
//            int id = rs.getInt(1);
//            String name = rs.getString(2);
//            System.out.println(id+"......"+name);
//        }
//    }
//        String sql ="select * from helloworld.students where name='"+nameString+"'";
//        ResultSet rs = stat.executeQuery(sql);
//
//        while (rs.next()){
//            int id = rs.getInt(1);
//            String name= rs.getString(2);
//            System.out.println(id+"......"+name);
//        }
//
//    }

    public void batchExcuteByStatement() throws Exception {
        int num=100000;
        Statement stat = this.util.getStatement();
        long start = System.currentTimeMillis();

        for (int x=0;x<num;x++){
            String sql ="insert into helloworld.students(name) value('张三"+x+"')";
            stat.executeUpdate(sql);
        }


        long end = System.currentTimeMillis();
        System.out.println(end-start);
        this.util.close(null,stat,this.conn);

    }

//    public void batchExcuteBypreparedstatement(){
//        int num =100000;
//        String sql ="insert into  helloworld.student(name) value(?)";
//        this.util.getStatement(sql);
//        for(int x=0)
//    }
    public static void main(String[] args) throws Exception {
        CRUD crud = new CRUD();
        //crud.createDatabase("dep");
      // crud.createTable("ljl");
     //  crud.insert();
       // crud.select();
        //crud.select("张三 ' or 1 or '");
     //   crud.select2("张三 ' or 1 or '");
        crud.batchExcuteByStatement();
    }
    //PreperedStatement除了可以防止SQL注入攻击，可以进行批处理操作，
    // 使用statement和preparestatement分别下个数据库汇总插入10万条数据

}
// 作业：编写JdbcUtil类，对JDBC的操作进行封装。