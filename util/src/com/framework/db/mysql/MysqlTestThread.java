package com.framework.db.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.framework.db.mongo.MianThread;
import com.framework.db.mongo.MongoDBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;


/***
 * *
 * 类名称：		MysqlDBUtil.java 
 * 类描述：   		
 * 创建人：		
 * 创建时间：		2016-12-7上午11:24:48 
 * 修改人：		liuxing
 * 修改时间：		2016-12-7上午11:24:48 
 * 修改备注：   		
 * @version
 */
public class MysqlTestThread implements Runnable{
	
	private static Connection conn;
    
    public MysqlMianThread mian ;
	private int ii ;
	
	public MysqlTestThread( MysqlMianThread mian ,int ii ){
		this.mian =mian ;
		this.ii = ii ;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long t1 = System.currentTimeMillis() ;
		conn = MysqlJDBCUtil.getConnection();
		PreparedStatement state=null;    //创建PreparedStatement 对象

		String sql = "INSERT INTO person(name,age) VALUES( ?,? )";
		
		// 查询条件的参数设定
		int local = ii * 100000 ;
		for(int i=local ;i< ( local + 1000);i++){
			 try {
				state = conn.prepareStatement(sql);
				state.setString( 1 , "xing" );
				state.setInt( 2 , i ); 
				state.executeUpdate();
			} catch (SQLException e) {}
		}
		//try {
			//conn.commit() ;
		//} catch (SQLException e) {}
		System.out.println( Thread.currentThread().getName() + "=====此线程耗时=========" + ( System.currentTimeMillis() - t1 ) );
		mian.callBack( (int) ( System.currentTimeMillis() - t1 ) ) ;

        //操作后释放资源
        MysqlJDBCUtil.release(conn, state, null);
	}
    
}
