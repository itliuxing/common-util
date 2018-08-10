package com.framework.net.mina.server;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/****
 * *
 * 类名称：		MInaServer.java 
 * 类描述：   		socket 服务器
 * 创建人：		
 * 创建时间：		2015-5-6下午12:09:14 
 * 修改人：		liuxing
 * 修改时间：		2015-5-6下午12:09:14 
 * 修改备注：   		
 * @version
 */
public class MinaServer {
	
	private static Logger logger = Logger.getLogger( MinaServer.class );

	private static int PORT = 3005;
	
	public static void main(String[] args) {
		IoAcceptor acceptor = null;   // 创建连接
		try {
			// 创建一个非阻塞的server端的Socket
			acceptor = new NioSocketAcceptor();
			MinaServerHandler.times = System.currentTimeMillis() ;		//纪录一个简单的请求要消耗多少的时间
			// 设置日志过滤器
			acceptor.getFilterChain().addLast("logger",new LoggingFilter());
			// 设置过滤器（使用Mina提供的文本换行符编解码器）
			acceptor.getFilterChain().addLast( //添加消息过滤器，可以有很多种的哦
					"coded",
					new ProtocolCodecFilter(new TextLineCodecFactory(Charset
							.forName("UTF-8"),
							LineDelimiter.WINDOWS.getValue(),
							LineDelimiter.WINDOWS.getValue())));
			
			// 设置读取数据的缓冲区大小
			acceptor.getSessionConfig().setReadBufferSize(2048);
			// 读写通道10秒内无操作进入空闲状态
			acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 5);
			// 绑定逻辑处理器
			acceptor.setHandler(new MinaServerHandler()); // 添加业务处理
			// 绑定端口
			acceptor.bind(new InetSocketAddress(PORT));
			logger.info("服务端启动成功...     端口号为：" + PORT);
		} catch (Exception e) {
			logger.error("服务端启动异常....", e);
			e.printStackTrace();
		}
	}

}
