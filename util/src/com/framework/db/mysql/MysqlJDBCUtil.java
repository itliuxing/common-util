package com.framework.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/***
 * *
 * 类名称：		MysqlJDBCUtil.java 
 * 类描述：   		mysql jdbc 连接
 * 创建人：		
 * 创建时间：		2016-12-7上午11:51:49 
 * 修改人：		liuxing
 * 修改时间：		2016-12-7上午11:51:49 
 * 修改备注：   		
 * @version
 */
public class MysqlJDBCUtil {
	
	private static String url = "jdbc:mysql://localhost:3306/reptile";  
    private static String user = "root";  
    private static String psw = "root";  
	
	static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	
    //获得连接
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, psw );
            System.out.println("connection is successful!");
        } catch (SQLException e) {
            System.out.println("connection error！");
            e.printStackTrace();
        }
        return conn;
    }
    
    //释放JDBC资源（关闭顺序与声明时的顺序相反）
    public static void release(Connection conn,Statement state,ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(state != null){
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
