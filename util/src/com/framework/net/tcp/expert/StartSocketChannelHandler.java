package com.framework.net.tcp.expert;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.sound.sampled.AudioFormat.Encoding;

import org.apache.log4j.Logger;

import com.framework.net.tcp.expert.protocol.TCPProtocol;
import com.framework.net.tcp.expert.protocol.TCPProtocolImpl;
import com.framework.net.tcp.expert.protocol.TCPProtocolRunable;
import com.framework.other.DecodeUtil;
import com.framework.thread.CustomExecutorService;

/**
 * *
 * 类名称：		StartSocketChannelHandler.java 
 * 类描述：   		具体socket执行服务类
 * 创建人：		
 * 创建时间：		2016-5-23上午10:33:00 
 * 修改人：		liuxing
 * 修改时间：		2016-5-23上午10:33:00 
 * 修改备注：   		
 * @version
 */
public class StartSocketChannelHandler implements Runnable {
	private static final Logger log = Logger.getLogger(StartSocketChannelHandler.class);

	// 缓冲区大小  
    private static final int BufferSize = 1024;  
    // 超时时间，单位毫秒  
    private static final int TimeOut = 3000;  
    // 本地监听端口  
    private int ListenPort = 1978;  
    
	private Integer  processCount = 0;
	private ServerSocketChannel serverSocketChannel;
	private boolean startFlag = true;
	
	//选择器和协议
	private Selector selector ;
	private TCPProtocol protocol ;

	public StartSocketChannelHandler(Integer ListenPort) {
		super();
		
		CustomExecutorService.getInstance() ;
		
		this.ListenPort = ListenPort;
		init();
	}

	private void init() {
		try {
			 // 创建选择器  
	        selector = Selector.open();  
	        // 打开监听信道  
	        ServerSocketChannel listenerChannel = ServerSocketChannel.open();  
	        // 与本地端口绑定  
	        listenerChannel.socket().bind(new InetSocketAddress( InetAddress.getLocalHost(), ListenPort));  
	        log.debug("TCP NIO SERVER 初始化成功  ,服务器IP：" + InetAddress.getLocalHost() + "  端口号 : " + ListenPort + "  服务已启动...");
	        // 设置为非阻塞模式  
	        listenerChannel.configureBlocking(false);  
	        // 将选择器绑定到监听信道,只有非阻塞信道才可以注册选择器.并在注册过程中指出该信道可以进行Accept操作  
	        listenerChannel.register(selector, SelectionKey.OP_ACCEPT);  
	        // 创建一个处理协议的实现类,由它来具体操作  
	        protocol = new TCPProtocolImpl(BufferSize);  
	        
		} catch (IOException e) {
			if (log.isDebugEnabled()) {
				log.error(e, e);
				log.debug("  服务器已启动  ******************");
			}
		}
	}

	public void run() {
		while (startFlag) {
			// true -已启动， false - 停止,如果工具类有修改则此处也将自动释放掉端口的监听
			boolean startFlagTmp = TCPServerUtil.getInstance().serverChannelFlagMap.get(ListenPort);
			if( startFlagTmp ){
				try {
		            selector.select();  
					// 等待某信道就绪(或超时)  
		            //if (selector.select(TimeOut) == 0) {// 监听注册通道，当其中有注册的 IO  
		                                                // 操作可以进行时，该函数返回，并将对应的  
		                                                // SelectionKey 加入 selected-key  
		                                                // set  
		            //    System.out.print("独自等待.");  
		            //    continue;  
		            //}  
					
					/*Set<SelectionKey> keys = selector.selectedKeys();
				    Iterator<SelectionKey> iter = keys.iterator();
				    while(iter.hasNext()){
				      SelectionKey key = iter.next();
				      if(key.isConnectable());//连接成功&正常
				      else if (key.isReadable()) {// 判断是否有数据发送过来  
				      
				      // 返回创建此键的通道，接受客户端建立连接的请求，并返回 SocketChannel 对象  
				        SocketChannel clientChannel = ((ServerSocketChannel) key.channel())  
				                .accept();  
				        // 非阻塞式  
				        clientChannel.configureBlocking(false);  
				        // 注册到selector  
				        clientChannel.register(key.selector(), SelectionKey.OP_READ,   ByteBuffer.allocate(1024) );  
				      }else  if(key.isReadable()){//可读
				    	  CustomExecutorService.getInstance().getExecutorService().execute( new TCPProtocolRunable( key) );
				      }
				      iter.remove();
				    }*/
		            // 取得迭代器.selectedKeys()中包含了每个准备好某一I/O操作的信道的SelectionKey  
		            // Selected-key Iterator 代表了所有通过 select() 方法监测到可以进行 IO 操作的 channel  
		            // ，这个集合可以通过 selectedKeys() 拿到  
	                Set<SelectionKey> keyIter = selector.selectedKeys() ;  
	                //for(final SelectionKey key : keyIter){ 
	                Iterator<SelectionKey> iter = keyIter.iterator();
	                while( iter.hasNext() ){
	                	SelectionKey key = iter.next();
	                    try {  
	                        if (key.isAcceptable()) {  
	                            // 有客户端连接请求时  
	                            //protocol.handleAccept(key);  
	                        	// 返回创建此键的通道，接受客户端建立连接的请求，并返回 SocketChannel 对象  
	    				        SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();  
	    				        // 非阻塞式  
	    				        clientChannel.configureBlocking(false);  
	    				        // 注册到selector  
	    				        clientChannel.register(key.selector(), SelectionKey.OP_READ,   ByteBuffer.allocate(1024) ); 
	                        }else if (key.isReadable()) {// 判断是否有数据发送过来  
	                            // 从客户端读取数据  
	                            //protocol.handleRead(key);  
	                        	CustomExecutorService.execute( new TCPProtocolRunable( key) );
	                        }  
	                        if (key.isValid() && key.isWritable()) {// 判断是否有效及可以发送给客户端  
	                            //protocol.handleWrite(key);  // 客户端可写时  
	                        }  
	                        iter.remove();
	                    } catch (Exception ex) {  
	                        // 出现IO异常（如客户端断开连接）时移除处理过的键  
	                        keyIter.clear();  
	                        break ;  
	                    }  
	                }  
	                keyIter.clear() ;
				} catch (Exception e) {
					log.error(e, e);
				}
				
			}
		}

	}
		
	
	/***
	 * *
	 * 类名称：		ReceiveHandler.java 
	 * 类描述：   		业务接收处理类
	 * 创建人：		
	 * 创建时间：		2015-9-24下午1:59:38 
	 * 修改人：		liuxing
	 * 修改时间：		2015-9-24下午1:59:38 
	 * 修改备注：   		
	 * @version
	 */
	class ReceiveHandler implements Runnable {
		private SocketChannel socketChannel;
		private ByteBuffer buffer ;
		private long startTiem ;
		
		public ReceiveHandler(SocketChannel socketChannel){
			this.socketChannel = socketChannel ;
			startTiem = System.currentTimeMillis() ;
		}
		
		public void run() {
			//try {
				//log.debug("ReceiveHandler -- 线程开始执行 ");
				while ( socketChannel.isOpen() && socketChannel.isConnected() ) {		//放置主线程关闭后,服务还在运行
					//if( TCPServerUtil.getInstance().serverChannelFlagMap.get( serverPort ) ){		//按理说数据处理这一个级别是不需要再对上一层的业务做处理的,注释
						handle();
					//}else{
					//	socketChannel.close() ;
					//	socketChannel = null ;
					//	break ;
					//}
				}
				//log.debug("ReceiveHandler --  socketChannel 客户端连接关闭，退出本线程 ");
				//log.debug("ReceiveHandler -- 线程执行结束");
				synchronized ( this ) {
					//StaticConfig.concurrenceCount -= 1 ;
				}
				log.debug("当前请求耗时：" + (System.currentTimeMillis() - startTiem) + " 毫秒" );
			//} catch (IOException e) {}
		}
		
		public void handle() {
			try {
				//receive();
				buffer = ByteBuffer.allocate(200);		//根据实际的需要,划定请求数据长度
				log.debug("当前请求耗时：" + (System.currentTimeMillis() - startTiem) + " 毫秒" );
				if ( socketChannel.read( buffer ) > 0 ) {		//大于0时，说明连接还存在，小于或者等于0时，说明连接已经断开
					// 将缓冲区准备为数据传出状态
					buffer.flip();
					// 传输十六进制的编码格式
					byte[] MessageBytes = buffer.array();
					//log.debug( "客户端地址：" + socketChannel.socket().getInetAddress() + "客户端端口：" + socketChannel.socket().getPort()  );
					//log.debug("byte转十六进制的字符码：" + bytesToHexString( MessageBytes ) );
					//log.debug("byte转二进制的字符码：" + bytesToString( MessageBytes ).trim() );
					//byte[] messagebytes =  encoding("you request is success") ;		//dowmlinkMessage.getBytes() ;
					if( socketChannel != null &&  socketChannel.isOpen() ){				//在请求端断掉服务之后，信息发送不执行
						String s = "you request is success" ;
						socketChannel.write( ByteBuffer.wrap( s.getBytes() ) );			//回馈信息
					}
					log.debug("当前请求耗时：" + (System.currentTimeMillis() - startTiem) + " 毫秒" );
				}else{
					socketChannel.finishConnect() ;
					socketChannel.close() ;
					log.debug("当前请求耗时：" + (System.currentTimeMillis() - startTiem) + " 毫秒" );
				}
			} catch (Exception e) {
				log.error(e, e);
				try {
					socketChannel.close() ;
				} catch (IOException ee) {
				}
			}
		}
	}
	
	public static byte[] asciiToByte( byte[] message ){
		byte[] tChars=new byte[message.length];
	      
	      for(int i=0;i<message.length;i++)
	       tChars[i]=(byte)message[i];
	     return tChars ;
	}

	/**
	 * 二进制转换为字符型
	 * @param message
	 * @return
	 */
	public static String bytesToString( byte[] message ){
		return DecodeUtil.toStringHex( DecodeUtil.byte2hex(message) );
	}
	
	/***
	 * 二进制转换成十六进制
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src){  
	    StringBuilder stringBuilder = new StringBuilder("");  
	    if (src == null || src.length <= 0) {  
	        return null;  
	    }  
	    for (int i = 0; i < src.length; i++) {  
	        int v = src[i] & 0xFF;  
	        String hv = Integer.toHexString(v);  
	        if (hv.length() < 2) {  
	            stringBuilder.append(0);  
	        }  
	        stringBuilder.append(hv).append(" ");  
	    }  
	    return stringBuilder.toString();  
	}  
	
	/***
	 * 传入下行的数据，将数据前面加上字符的长度
	 * @param message
	 * @return
	 */
	public static byte[] encoding( String message ){
		byte[] bytes = null ;
		if( message != null ){
			bytes = new byte[ message.length() + 2 ] ;
			byte[] byteArray = message.getBytes() ;
			//判断字符的长度大于127
			if( byteArray.length + 2 > 127 ){
				bytes[0] = (byte) (0xff & ((byteArray.length + 2) % 127) )  ;
				bytes[1] = (byte) (0xff & 127 );
			}else{
				bytes[0] = (byte) (0xff & 0 );
				bytes[1] = (byte) (0xff &  (byteArray.length + 2) ) ;
			}
			for( int i=2;i<bytes.length;i++ ){
				bytes[i] = byteArray[i-2] ;
			}
		}
		return bytes ;
		
	}
}
