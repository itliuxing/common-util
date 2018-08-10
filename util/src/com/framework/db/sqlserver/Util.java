package com.framework.db.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * 
 * This class is used for ...   
 * @author liux  
 * @version   
 *       1.0, 2013-4-22 下午01:22:13   
 * information :
 *		 DButil类----主要作用于，对数据库的链接及操作
 */
public class Util {
	private String com,driver,sql,local,port,data,name,use,password;
	private Connection con;
	private static Util util ;
	/**
	 * 构造方法，在构造自己的时候得到配置文件里面的与数据库有关的数据
	 */
	public Util(){
		Config config = new Config() ; //ConfigReadOrWrite.getInstance().getConfig() ;
		com=config.getDBDriver();
		driver="jdbc";
		sql= config.getDBDatabaseName() ;
		local = config.getDBAddress() ;
		port = config.getDBPort() ;
		data =  config.getDBDatabase() ;
		use = config.getDBUser() ;
		password = config.getDBPassword();
		con=this.connection();
	}
	
	public synchronized static Util getInstanceUtil(){
		if( util == null ){
			util = new Util() ;
		}
		return util ;
	}
	
	/**
	 * 得到管道并建立链接
	 * @return		Connection
	 */
	private Connection connection(){
		try {
			if( con == null || con.isClosed() ){
				StringBuffer url = new StringBuffer() ;
				//得到的是链接数据库的URL
				//sqlserver
				String url1 =driver+":"+sql+"://"+local+":"+port+";database="+data+";user="+use+";password="+password;
				//jdbc:mysql://localhost:3306/sample_db?user=root&password=your_password
				//url.append(driver).append(":").append(sql).append("://").append(local) ;
				//url.append(":").append(port).append("/").append(data).append("?user=").append(use).append("&password=") ;
				//url.append(password).append("&useUnicode=true&characterEncoding=UTF-8") ;
				//com在构造自己的时候应景将数据加入进去----就是配置文件的驱动（指的是何种数据库）
				Class.forName(com);
				return DriverManager.getConnection( url.append(url1).toString() );
			}
		} catch (Exception e) {
			System.out.println( e.getMessage() );
			try
		    {
				  con.close();
		    }catch(Exception ex1){
		        System.out.println("数据库连接失败！");
		    }
		}
		return con ;
	}
	
	/**
	 * 增删改都可以用此方法
	 * @param sql
	 * @param object
	 * @return	int
	 */
	public int udate(String sql,Object ... object ) {
		try {
			//得到建立的管道
			//con=this.connection();
			//得到链接到数据库的preparedstatement-----传入SQL语句
			PreparedStatement prepared=con.prepareStatement(sql);
			//得到DAO层的数组----并开始加入到-------preparedstatement
			if(object != null){
				for(int sign=0;sign<object.length;sign++){
					prepared.setObject(sign+1,object[sign]);
				}
			}
			return prepared.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 查询的事件util方法
	 * @param sql
	 * @param object
	 * @return ResultSet
	 */
	public ResultSet select(String sql,Object ... object ){
		//得到建立的管道
		//con=this.connection();
		try {
			//得到链接到数据库的preparedstatement-----传入SQL语句
			PreparedStatement perpared=con.prepareStatement(sql);
			if(object != null){
				//得到DAO层的数组----并开始加入到-------preparedstatement
				for(int sign=0;sign<object.length;sign++){
					perpared.setObject(sign+1,object[sign]);
				}
			}
			return perpared.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * 此方法做两件事情，
	 * 第一如果档期那链接出错就返回错误，
	 * 第二件事情如果当前服务加载的时候链接错了，那么会将当前对象置空当前对象，因为是单例所以必须置空
	 * @return
	 * @author liux
	 */
	public boolean isConnect(){
		boolean remark = false ;
		if ( con != null  ) {
			try {
				if( ! con.isClosed() ){
					remark = true ;
				}
			} catch (SQLException e) {
				util = null ;
			}
		}else{
			util = null ;
		}
		return remark ;
	}
	
	
	
	public static void main(String[] args) {
		Util utiel = new Util();
		utiel.udate("insert into tb_user(name,sex,age) values(?,?,?)", "赵四","女",54) ;
	}
}
