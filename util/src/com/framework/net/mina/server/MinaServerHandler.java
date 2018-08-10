package com.framework.net.mina.server;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import socket.mina.Config;

/***
 * *
 * 类名称：		MinaServerHandler.java 
 * 类描述：   		mina 服务处理类
 * 创建人：		
 * 创建时间：		2015-5-6下午12:12:10 
 * 修改人：		liuxing
 * 修改时间：		2015-5-6下午12:12:10 
 * 修改备注：   		
 * @version
 */
public class MinaServerHandler extends IoHandlerAdapter {
	
	public static Logger logger = Logger.getLogger(MinaServerHandler.class);
	
	public static Long times ;

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("服务端与客户端创建连接...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("服务端与客户端连接打开...");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = message.toString();
		logger.info("服务端接收到的数据为：" + msg + ",这是第" +Config.mark+ "请求.");
		//System.out.println("服务端接收到的数据为：" + msg + ",这是第" +Config.mark+ "请求.");
		Config.mark += 1 ;
		if ("bye".equals(msg)) { // 服务端断开连接的条件
			session.close( true );
		}
		Date date = new Date();
		session.write(date);
	}
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		//在信息发送成功后，服务端主动关闭与客户端的链接
//		session.close( true ); //发送成功后主动断开与客户端的连接
		logger.info("服务端发送信息成功...");
	}
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("服务端关闭连接...，总耗时：" + ( System.currentTimeMillis() - times ) );
	}
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		logger.info("服务端进入空闲状态...，总耗时：" + ( System.currentTimeMillis() - times ) );
	}
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("服务端发送异常...", cause);
	}

}
