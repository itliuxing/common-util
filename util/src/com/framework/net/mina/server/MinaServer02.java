package com.framework.net.mina.server;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/****
 * *
 * 类名称：		MInaServer.java 
 * 类描述：   		socket 服务器		模拟通信服务商的短信服务
 * 创建人：		
 * 创建时间：		2015-5-6下午12:09:14 
 * 修改人：		liuxing
 * 修改时间：		2015-5-6下午12:09:14 
 * 修改备注：   		
 * @version
 */
public class MinaServer02 {
	
	private static Logger logger = Logger.getLogger( MinaServer02.class );
	
	private static int PORT = 3005;
	public static void main(String[] args) {
		IoAcceptor acceptor = null;
		try {
			// 创建一个非阻塞的server端的Socket
			acceptor = new NioSocketAcceptor();
			// 设置过滤器
			acceptor.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new TextLineCodecFactory(Charset
							.forName("UTF-8"),
							LineDelimiter.WINDOWS.getValue(),
							LineDelimiter.WINDOWS.getValue())));

			// 获得IoSessionConfig对象
			IoSessionConfig cfg = acceptor.getSessionConfig();
			// 读写通道10秒内无操作进入空闲状态
			cfg.setIdleTime(IdleStatus.BOTH_IDLE, 100);

			// 绑定逻辑处理器
			acceptor.setHandler(new MinaServerHandler02());
			// 绑定端口
			acceptor.bind(new InetSocketAddress(PORT));
			logger.info("服务端启动成功...     端口号为：" + PORT);
		} catch (Exception e) {
			logger.error("服务端启动异常....", e);
			e.printStackTrace();
		}
	}
}
