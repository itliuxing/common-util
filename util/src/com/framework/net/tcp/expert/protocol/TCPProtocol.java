package com.framework.net.tcp.expert.protocol;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/****
 * *
 * 类名称：		TCPProtocol.java 
 * 类描述：   		协议接口类
 * 创建人：		
 * 创建时间：		2016-5-23下午12:06:02 
 * 修改人：		liuxing
 * 修改时间：		2016-5-23下午12:06:02 
 * 修改备注：   		
 * @version
 */
public interface TCPProtocol {
	
	/**  
	    * 接收一个SocketChannel的处理  
	    * @param key  
	    * @throws IOException  
	    */  
	   void handleAccept(SelectionKey key) throws IOException;  
	     
	   /**  
	    * 从一个SocketChannel读取信息的处理  
	    * @param key  
	    * @throws IOException  
	    */  
	   void handleRead(SelectionKey key) throws IOException;  
	     
	   /**  
	    * 向一个SocketChannel写入信息的处理  
	    * @param key  
	    * @throws IOException  
	    */  
	   void handleWrite(SelectionKey key) throws IOException; 

}
