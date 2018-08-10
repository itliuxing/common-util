package com.framework.net.telnet.socket.client;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.framework.net.telnet.callback.CallbackI;
import com.framework.net.telnet.dto.SocketRequestDto;




/***
 * *
 * 类名称：		MinaClient.java 
 * 类描述：   		Mina服务端自带多线程处理，因此不必做长链接的处理
 * 创建人：		
 * 创建时间：		2016-9-7上午10:24:05 
 * 修改人：		liuxing
 * 修改时间：		2016-9-7上午10:24:05 
 * 修改备注：   		
 * @version
 */
public class MinaClient {
	
	private static Logger log = Logger.getLogger( MinaClient.class );
	private static String host = "localhost";
	private static int port = 8081;
	private static int connTimeOut = 60 * 1000; // 链接超时 单位 毫秒
	

	private static MinaClient instance;

	private MinaClient() {
	}

	public synchronized static MinaClient getInstance() {
		if (instance == null) {
			instance = new MinaClient();
		}
		return instance;
	}
	
	/****
	 * 创建socket
	 * @param socketRequestDto
	 */
	public static void createSocket(  CallbackI callback , SocketRequestDto socketRequestDto  ){
		// 创建一个socket连接        
        NioSocketConnector connector=new NioSocketConnector();  
        // 获取过滤器链          
        DefaultIoFilterChainBuilder chain=connector.getFilterChain();  
          
        // 添加编码过滤器 处理乱码、编码问题     --- 传输对象类型数据
        chain.addLast("objectFilter",new ProtocolCodecFilter(new ObjectSerializationCodecFactory()) );  
        
        MinaClientHanlder handler = new MinaClientHanlder( callback, socketRequestDto ) ;
        // 消息核心处理器       -----数据的封装发送，解封装接收均在处理类里面
        connector.setHandler( handler );  
        //connector.setHandler( new MinaClientHanlder() );  
        // 设置链接超时时间       
        connector.setConnectTimeoutCheckInterval( connTimeOut );  
        // 连接服务器，知道端口、地址      
        ConnectFuture cf = connector.connect( new InetSocketAddress(host,port) );  
        // 等待连接创建完成      
        cf.awaitUninterruptibly();  
        cf.getSession().getCloseFuture().awaitUninterruptibly();  
        connector.dispose();  
	}

	
}
