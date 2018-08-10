package com.framework.db.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/****
 * *
 * 类名称：		TestMongoFast.java 
 * 类描述：   		单线程测试，每个线程自己处理一个文档数据，不再和其他线程共用一个连接
 * 创建人：		
 * 创建时间：		2016-12-7上午10:21:15 
 * 修改人：		liuxing
 * 修改时间：		2016-12-7上午10:21:15 
 * 修改备注：   		
 * @version
 */
public class TestMongoFast implements Runnable{
	
	public MongoDBUtil mondb ;
	public MianThread mian ;
	private int ii ;
	
	public TestMongoFast( MongoDBUtil mondb , MianThread mian ,int ii ){
		this.mondb = mondb ;
		this.mian =mian ;
		this.ii = ii ;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long t1 = System.currentTimeMillis() ;
		DBCollection mongoDbCollection =  mondb.getMongoDb().getCollection( "person" );
		// 查询条件的参数设定
		int local = ii * 100000 ;
		for(int i=local ;i< (local + 100000);i++){
			DBObject dbObjectQueryParam = new BasicDBObject();
			dbObjectQueryParam.put("name", "liuxing");
			dbObjectQueryParam.put("age", i);
		// 执行查询操作
			WriteResult writeResult = mongoDbCollection.insert( dbObjectQueryParam ) ;if (null != writeResult) {
	            if (writeResult.getN() < 1) {
	            	//System.out.println( "mongodb 插入数据成功." );
	            }
	        }
		}
		System.out.println( Thread.currentThread().getName() + "=====此线程耗时=========" + ( System.currentTimeMillis() - t1 ) );
		mian.callBack( (int) ( System.currentTimeMillis() - t1 ) ) ;
	}
	
	

}
