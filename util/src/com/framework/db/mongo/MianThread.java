package com.framework.db.mongo;

import java.util.ArrayList;
import java.util.List;

import com.framework.thread.CustomExecutorService;

/****
 * *
 * 类名称：		MianThread.java 
 * 类描述：   		主线程-----mongodb 数据写入多线程测试
 * 创建人：		
 * 创建时间：		2016-12-7上午10:25:14 
 * 修改人：		liuxing
 * 修改时间：		2016-12-7上午10:25:14 
 * 修改备注：   		
 * @version
 */
public class MianThread {
	
	public static List<Integer> array = new ArrayList<Integer>();
	
	public synchronized void callBack( int times ){
		array.add( times ) ;
	}
	
	public static void main(String[] args) {
		MianThread main = new MianThread () ;
		MongoDBUtil mondb= MongoDBUtil.getInstance() ;
		
		int i = 10 ;
		for(int p=1;p<=i;p++){
			CustomExecutorService.getInstance().execute( new TestMongoFast( mondb, main ,p ) ) ;
		}
		
		while( array.size() < i ){
			try {
				Thread.sleep( 1000 * 5 ) ;
			} catch (InterruptedException e) {}
		}
		int count = 0 ;
		for( int times : array ){
			count += times ;
		}
		System.out.println( "平均每个线程耗时：" + ( count / ( 1000 * i ) ) + "秒");
		System.out.println( "每个线程耗时总值：" + ( count / 1000 )  + "秒" );
		CustomExecutorService.getInstance().destory() ;
	}

}
