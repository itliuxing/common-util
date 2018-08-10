package com.framework.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/***
 * *
 * 类名称：		CustomExecutorService   
 * 类描述：		线程池配置   
 * 创建人：		liuxing   
 * 创建时间：	2014-11-21 下午4:45:26   
 * 修改人：		liuxing   
 * 修改时间：	2016-10-21 下午4:45:26   
 * 修改备注：   
 * @version	1.1
 */
public class CustomExecutorService {

	private static Logger log = Logger.getLogger( CustomExecutorService.class ) ;
	
	private static ExecutorService executorService; 		// 线程池
	private static final int POOL_MULTIPLE = 0x0000000a ; 	// 线程池中单个CPU分配工作线程的数目（十六进制）
	private static CustomExecutorService instance;

	private CustomExecutorService() {
		// 创建一个线程池                            可用处理器数*线程池工作数
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_MULTIPLE);
	}

	/***
	 * 一次调用就可以了，很多地方其实不再需要再次调用，在系统启动的时候调用一次就可以了
	 * @return
	 */
	public synchronized static CustomExecutorService getInstance() {
		if (instance == null) {
			instance = new CustomExecutorService();
			log.info( "Thread pool instance success" ) ;
		}
		return instance;
	}
	
	/****
	 * 一次调用就可以了，在系统关闭的时候调用一次就可以了
	 */
	public static void destory() {
		log.info( "Thread pool shutdown ..." ) ;
		executorService.shutdown() ;
	}

	/****
	 * 具体执行线程的调用
	 * @param thread
	 */
	public static void execute( Runnable thread ) {
		if( executorService != null ){
			executorService.execute(thread);
		}else{
			log.error( "Thread pool haven't instance,please instance Thread pool." ) ;
		}
	}
	
	public static void main(String[] args) {
		CustomExecutorService.getInstance() ;		//线程池初始化 --- 系统启动时调用一次就ok
		
		CustomExecutorService.execute( new Thread() ) ;
		
		CustomExecutorService.destory() ;
	}

}
