package com.framework.net.tcp.expert.protocol;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

/****
 * *
 * 类名称：		TCPProtocolImpl.java 
 * 类描述：   		简单文本协议实现类
 * 创建人：		
 * 创建时间：		2016-5-23下午12:07:27 
 * 修改人：		liuxing
 * 修改时间：		2016-5-23下午12:07:27 
 * 修改备注：   		
 * @version
 */
public class TCPProtocolRunable implements Runnable {

	private int bufferSize = 1024;  
	private SelectionKey key ;
	  
    public TCPProtocolRunable( SelectionKey key) {  
        this.key = key ;
    }  
    
    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	try {
			handleRead() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
    }
  
    public void handleAccept(SelectionKey key) throws IOException {  
        // 返回创建此键的通道，接受客户端建立连接的请求，并返回 SocketChannel 对象  
        SocketChannel clientChannel = ((ServerSocketChannel) key.channel())  
                .accept();  
        // 非阻塞式  
        clientChannel.configureBlocking(false);  
        // 注册到selector  
        clientChannel.register(key.selector(), SelectionKey.OP_READ,  
                ByteBuffer.allocate(bufferSize));  
    }  
  
    public void handleRead() throws IOException {  
        // 获得与客户端通信的信道  
        SocketChannel clientChannel = (SocketChannel) key.channel();  
        // 得到并清空缓冲区  
        ByteBuffer buffer = (ByteBuffer) key.attachment();  
        buffer.clear();  
        // 读取信息获得读取的字节数  
        long bytesRead = clientChannel.read(buffer);  
        if (bytesRead == -1) {  
            // 没有读取到内容的情况  
            clientChannel.close();  
        } else {  
            // 将缓冲区准备为数据传出状态  
            buffer.flip();  
            // 将字节转化为为UTF-16的字符串  
            String receivedString = Charset.forName("UTF-8").newDecoder()  
                    .decode(buffer).toString();  
            // 控制台打印出来  
            System.out.println("接收到来自"  
                    + clientChannel.socket().getRemoteSocketAddress() + "的信息:"  
                    + receivedString);  
            // 准备发送的文本  
            String sendString = "你好,客户端. @" + new Date().toString()  
                    + "，已经收到你的信息" + receivedString;  
            buffer = ByteBuffer.wrap(sendString.getBytes("UTF-8"));  
            clientChannel.write(buffer);  
            // 设置为下一次读取或是写入做准备  
            //key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);  
        }  
    }  
  
    public void handleWrite(SelectionKey key) throws IOException {  
        // do nothing  
    }  

}
