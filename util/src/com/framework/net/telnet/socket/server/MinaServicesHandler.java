/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.socket.server;

import java.net.InetSocketAddress;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.framework.net.telnet.dto.SocketRequestDto;
import com.framework.net.telnet.dto.SocketResponseDto;

/**
 * *
 * 类名称：		CustomServicesHandler.java 
 * 类描述：   		socket服务器接收数据处理类
 * 创建人：		
 * 创建时间：		2016-9-7上午9:49:50 
 * 修改人：		liuxing
 * 修改时间：		2016-9-7上午9:49:50 
 * 修改备注：   		
 * @version
 */
public class MinaServicesHandler extends IoHandlerAdapter {
	public static Logger logger = Logger.getLogger(MinaServicesHandler.class);

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		SocketRequestDto socketRequestDto = (SocketRequestDto) message;

		//将客户端发送过来的数据，交给处理类去处理，处理类再做处理的转发操作，整个 Mina 的处理就成型了 
		//SocketResponseDto socketResponseDto =  SocketBusinessHandler.getInstance().handleRequest(socketRequestDto);
		//session.write(socketResponseDto);
		
		session.write( SocketBusinessHandler.handleRequest(socketRequestDto) );
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// session.close();
		//logger.error("服务端发送信息成功...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.error("服务端发送异常...", cause);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
		//logger.info("服务端与客户端创建连接...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);

		InetSocketAddress remoteAddress = (InetSocketAddress) session.getRemoteAddress();

		String clientIp = remoteAddress.getAddress().getHostAddress();
		int port = remoteAddress.getPort();
		logger.info("接收来自客户端 :" + clientIp + "的连接, port : " + port + "    LongConnect Server opened Session ID =" + String.valueOf(session.getId()));
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		//logger.info("客户端 关闭 ...");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		super.sessionIdle(session, status);
		logger.info("服务端进入空闲状态...");
	}

	static class Initialization {
		private static HashMap<String, IoSession> clientMap = new HashMap<String, IoSession>();

		public static HashMap<String, IoSession> getClientMap() {
			return clientMap;
		}

	}

}
