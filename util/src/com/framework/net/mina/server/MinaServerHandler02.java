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
public class MinaServerHandler02 extends IoHandlerAdapter {
	
	public static Logger logger = Logger.getLogger(MinaServerHandler02.class);
	
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String phoneMes = message.toString();
		String[] megs=phoneMes.split(";");
		String sendPhone = megs[0];
		String receivePhone = megs[1];
		String mes = megs[2];
		logger.info("发送人手机号码：" + sendPhone);
		logger.info("接受人手机号码：" + receivePhone);
		logger.info("发送信息：" + mes);

		// 短信信息存入移动服务端数据库或者写入手机短信转发队列
		// ............

		session.write("发送成功！"); // 告诉手机发送信息成功啦
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		session.close( true );
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("服务端发送异常...", cause);
	}
}
