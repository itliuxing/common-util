package com.framework.net.mina.server;

import java.nio.ByteBuffer;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.framework.net.mina.entity.PhoneMessage;

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
 * @param <HeapByteBuffer>
 */
public class MinaServerHandler03<HeapByteBuffer> extends IoHandlerAdapter {
	
	public static Logger logger = Logger.getLogger(MinaServerHandler03.class);
	
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println( message );
		//HeapByteBuffer buffer = (HeapByteBuffer) message;
		ByteBuffer bbytebuffer = ByteBuffer.allocate(1024 * 1024 ) ;
		//System.out.println( bbytebuffer );
		//System.out.println( buffer.toString() );
		PhoneMessage phoneMes = (PhoneMessage) message;
		String sendPhone = phoneMes.getSendPhone();
		String receivePhone = phoneMes.getReceivePhone();
		String mes = phoneMes.getMessage();
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
