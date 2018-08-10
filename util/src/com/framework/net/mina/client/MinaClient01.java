package com.framework.net.mina.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/***
 * *
 * 类名称：		MinaClient.java 
 * 类描述：   		mina 客户端调用
 * 创建人：		
 * 创建时间：		2015-5-6下午1:49:34 
 * 修改人：		liuxing
 * 修改时间：		2015-5-6下午1:49:34 
 * 修改备注：   		
 * @version
 */
public class MinaClient01 {	


	public static void main(String[] args) {
//		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 10 );
//		for( 	int i=0;i<1 ;i++){
//			executorService.execute(  new MimaClientThread()  );
//		}
		new Thread( new MimaClientThread() ).start() ;
	}
		
}

class MimaClientThread implements Runnable {
	
	private static Logger logger = Logger.getLogger(MinaClientHandler01.class);

	private static String HOST = "127.0.0.1";

	private static int PORT = 3005;

	@Override
	public void run() {
		// 创建一个非阻塞的客户端程序
		IoConnector connector = new NioSocketConnector();  // 创建连接
		// 设置链接超时时间
		connector.setConnectTimeout(30000);
		// 添加过滤器
		connector.getFilterChain().addLast(   //添加消息过滤器
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),
						LineDelimiter.WINDOWS.getValue())));
		// 添加业务逻辑处理器类
		connector.setHandler(new MinaClientHandler01());// 添加业务处理
		IoSession session = null;
		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(
					HOST, PORT));// 创建连接
			future.awaitUninterruptibly();// 等待连接创建完成
			session = future.getSession();// 获得session
			session.write("我爱你mina");// 发送消息
			session.write("bye");// 发送消息
		} catch (Exception e) {
			logger.error("客户端链接异常...", e);
		}

		session.getCloseFuture().awaitUninterruptibly();// 等待连接断开;如果我们直接调用完成的话，那么有可能收不到返回的值
		connector.dispose();
	}
	
}