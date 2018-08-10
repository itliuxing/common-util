package com.framework.net.tcp.expert;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


/***
 * *
 * 类名称：		TCPServerUtil.java 
 * 类描述：   		TCP Server 工工具类
 * 创建人：		
 * 创建时间：		2016-5-23上午10:26:34 
 * 修改人：		liuxing
 * 修改时间：		2016-5-23上午10:26:34 
 * 修改备注：   		
 * @version
 */
public class TCPServerUtil implements IServerChannelManager {
	
	private static final Logger log = Logger.getLogger(StartSocketChannelHandler.class);

	private static TCPServerUtil tcpServerUtil;

	private int port , processCount;

	public Map<Integer, Boolean> serverChannelFlagMap = new HashMap<Integer, Boolean>();
	
	
	/***
	 * 设置默认的端口，以及初始化部分数据
	 */
	private TCPServerUtil() {
		port = 8088 ;
		initTerminalData();
	}

	/***
	 * 获取对象
	 * @return
	 */
	public synchronized static TCPServerUtil getInstance() {
		if (tcpServerUtil == null) {
			tcpServerUtil = new TCPServerUtil();
		}
		return tcpServerUtil;
	}

	
	/***
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		TCPServerUtil.getInstance().startServerChannel() ;
		try {
			//Thread.sleep( 1000 * 5 ) ;	验证重复启动
			//TCPServerUtil.getInstance().startServerChannel() ;
			Thread.sleep( 1000 * 600 ) ;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TCPServerUtil.getInstance().stopServerChannel() ;
	}

	/***
	 * 初始化所有终端信息
	 */
	public void initTerminalData() {
		log.debug("TcpServerChannel  --  开始初始化数据 ");
		log.debug("TcpServerChannel ---  初始化数据成功 ");
	}
	
	/***
	 * 启动服务通道 代理
	 */
	public boolean startServerChannel() {
		return startSocketServer();
	}

	/**
	 * 启动
	 */
	private boolean startSocketServer() {
		service(port);
		return true;
	}
	
	/***
	 * 停止服务通道 代理
	 */
	public boolean stopServerChannel() {
		return stopSocketServer();
	}

	/**
	 * 关闭
	 */
	public boolean stopSocketServer() {
		serverChannelFlagMap.put( port , false );
		log.debug( "StartSocketChannelHandler --- 停止......processCount=" + processCount );
		System.exit( 0 ) ;
		return true ;
	}

	/***
	 * 服务启动
	 * @param serverPort
	 */
	public void service(Integer serverPort) {
		if( serverChannelFlagMap.get(port) != null && serverChannelFlagMap.get(port) ){
			log.debug( "TCP 端口 " + port + " 已启动..." );
		}else{
			serverChannelFlagMap.put( port , true );
			// 将端口与类路径名，实例化成一个线程对象
			StartSocketChannelHandler startSocketChannelHandler = new StartSocketChannelHandler(serverPort);
			// 线程池启动
			startSocketChannelHandler.run() ;
			
		}
	}

}
