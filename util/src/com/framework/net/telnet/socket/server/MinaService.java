/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.socket.server;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.framework.net.telnet.codec.AppSipcCodecFactory;

/****
 * *
 * 类名称：		MinaService.java 
 * 类描述：   		Mina 服务端启动器
 * 创建人：		
 * 创建时间：		2016-10-12下午4:57:40 
 * 修改人：		liuxing
 * 修改时间：		2016-10-12下午4:57:40 
 * 修改备注：   		
 * @version
 */
public class MinaService {
	
	private static final Logger logger = Logger.getLogger(MinaService.class);
	private static int PORT = 8081;
	private static int readerIdleTimeOut = 2 * 1000; 		// 响应超时 单位 毫秒
	private static IoAcceptor minaSocketAcceptor = null;

	private static MinaService instance;

	private MinaService() {}

	public static synchronized MinaService getInstance() {
		if (instance == null) {
			instance = new MinaService();
		}
		return instance;
	}

	/**
	 * 停止Socket 服务
	 */
	public static void stopMinaServer() {
		if (minaSocketAcceptor != null) {
			minaSocketAcceptor.dispose();
			minaSocketAcceptor=null;
		}
	}

	/**
	 * 启动Socket 服务
	 */
	public static void startMinaServer() {
		service();
	}

	private static void service() {
		try {
			// 创建一个非阻塞的server端的Socket
			minaSocketAcceptor = new NioSocketAcceptor( 100 );
			// 约定数据传输和解析方式：对象 ;替换成字符:将ObjectSerializationCodecFactory换成TextLineCodecFactory就可以了
			minaSocketAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));			//---使用系统的编码工具
			// 可以是字符串（TextLineCodecFactory ），可以是
			//acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter(new AppSipcCodecFactory(Charset.forName("UTF-8")))); -----使用自适配的编码工具
			
			
			// 获得IoSessionConfig对象
			IoSessionConfig cfg = minaSocketAcceptor.getSessionConfig();
			// 读写通道readerIdleTimeOut秒内无操作进入空闲状态，单位：秒
			cfg.setIdleTime(IdleStatus.BOTH_IDLE, readerIdleTimeOut);

			// 绑定逻辑处理器
			minaSocketAcceptor.setHandler(new MinaServicesHandler());
			// 绑定端口
			minaSocketAcceptor.bind(new InetSocketAddress(PORT));

			logger.info("Mina 服务端启动成功...     端口号为：" + PORT);
		} catch (Exception e) {
			logger.error("Mina服务端启动异常....", e);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		SocketBusinessHandler.getInstance() ;		//初始化部分数据调用
		MinaService.getInstance() ;
		MinaService.startMinaServer();
	}
	
}
