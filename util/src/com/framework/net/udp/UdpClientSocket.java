package com.framework.net.udp;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import com.framework.net.mina.entity.PhoneMessage;

public class UdpClientSocket {
	/** 
     * 连接对象 
     */  
    private static DatagramSocket ds = null;  
    /** 
     * 地址对象 
     */  
    private static SocketAddress address = null;  
      
    /** 
     * 测试客户端发包和接收回应信息的方法 
     */  
    public static void main(String[] args) throws Exception {  
        init();  
        while(true){  
            UdpClientSocket.send(address,"你好，在干什么呢 ？？？".getBytes());  
            UdpClientSocket.receive();  
            try {  
               Thread.sleep(3 * 1000);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
    /** 
     * 对连接和地址初始化 
     */  
    public static void init(){  
        try {  
            ds = new DatagramSocket(8899); // 邦定本地端口作为客户端  
            ds.setSoTimeout(1 * 5000);  
            address = new InetSocketAddress("127.0.0.1", 9930 );  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 向指定的服务端发送数据信息 
     */  
    public static void send(SocketAddress address,byte[] bytes){  
        try {  
        	PhoneMessage stu =new PhoneMessage() ;
            stu.setMessage("infomation") ;
            stu.setReceivePhone("15618293862") ;
            stu.setSendPhone("18791952828") ;
            DatagramPacket dp = new DatagramPacket( getByte(stu) , getByte(stu).length, address);  
            ds.send(dp);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static byte[] getByte(PhoneMessage stu){
    	ByteArrayOutputStream buffers = new ByteArrayOutputStream();
    	try {
            ObjectOutputStream out = new ObjectOutputStream(buffers);
            
            out.writeObject( stu );
            out.close();
        } catch (Exception e) {
            System.out.println( e.getMessage() );
            return null;
        }
        return buffers.toByteArray();
    }
    
    /** 
     * 接收从指定的服务端发回的数据 
     */  
    public static void receive(){  
        try {  
            byte[] buffer = new byte[1024];  
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);  
            ds.receive(dp);       
            byte[] data = new byte[dp.getLength()];  
            System.arraycopy(dp.getData(), 0, data, 0, dp.getLength());   
            System.out.println("服务端回应数据：" + new String(data,"UTF-8"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
