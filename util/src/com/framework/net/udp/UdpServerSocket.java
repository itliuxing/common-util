package com.framework.net.udp;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.framework.net.mina.entity.PhoneMessage;
import com.framework.net.tcp.expert.StartSocketChannelHandler;
import com.framework.thread.CustomExecutorService;
import com.mongodb.util.Util;

/***
 * 
 * This class is used for ...   
 * @author liux  
 * @version   
 *       1.0, 2013-4-19 上午11:22:16   
 * information :
 *		 UDP主服务类
 */
public class UdpServerSocket {
	
	public static final Logger log = Logger.getLogger(UdpServerSocket.class);
	
	private  DatagramSocket ds = null;  
	protected  DatagramPacket packet = null ;
    protected  SocketAddress address = null;  
    private  Boolean isContinue ;
    private  int port ;
    public   static UdpServerSocket serverSocket ;
	
    private UdpServerSocket(  ){
    	 CustomExecutorService.getInstance() ;
    	
    	this.port =  9930  ;
    	isContinue = true ;
    }
    
    public static UdpServerSocket getInstance(){
    	if( serverSocket == null ){
    		serverSocket = new UdpServerSocket() ;
    	}
    	return serverSocket ;
    }
    
    public static void main(String[] args) {
    	UdpServerSocket.getInstance().startService() ;
	}
    
    /***
     * 服务启动
     * @author liux
     */
	public void startService(){
		init() ;
		while ( isContinue ) {  
			this.receive();  
			try {
				Thread.sleep( 100 ) ;
			} catch (InterruptedException e) {}
			this.response(address,"没有   吃药中.......");  
	    }     
	}
	
	/***
	 * 初始化监听端口
	 * @return 返回执行的参数
	 * @author liux
	 */
	public void init(){
		 try {
				ds = new DatagramSocket( port  );  
				ds.setSoTimeout(0);
		        //serverManagerListener.updateLog( this.getClassName().append("初始化端口").append( port ).append("成功").toString() ) ;
		        isContinue = true ;
		 } catch (SocketException e) {
			// TODO Auto-generated catch block
			//serverManagerListener.updateLog( this.getClassName().append( e.getMessage() ).toString() ) ;
		}  
	}
	
	/***
	 * 服务端接受客户端发来的信息
	 * @return
	 * @author liux
	 */
	public void receive(){
		try {  
            byte[] buffer = new byte[1024];  
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);  
            ds.receive(packet);  
            address = packet.getSocketAddress();  
            CustomExecutorService.execute(new dataSaveService(this,packet));  
            buffer = null ;
            packet = null ;
            //接收对象，当然客户端跟服务端都要约定俗成，对象必须是一样的
            //Student stu = (Student)getObject( packet.getData() ) ;
            /*serverManagerListener.updateLog( 
            		new StringBuffer().append("接收来自").append( packet.getAddress() ).append("IP的信息：学生姓名==").append(
             		stu.getName() ).append( "===年龄：").append( stu.getAge() )
             		.append("===性别是：").append( stu.getSex() ).toString() 
             ) ;  */
            //此处调用线程池，创建线程处理数据
            //pool.execute(new dataSaveService(this,packet));  
		} catch (Exception e) {  
        	//serverManagerListener.updateLog( this.getClassName().append("报错信息：").append( e.getMessage() ).toString() ) ;
        }  
	}
	
	/***
	 * 服务停止
	 * @author liux
	 */
	public void stopService(){
		if( ds != null && ! ds.isClosed() ){
			ds.close() ;
			isContinue = false ;
			//serverManagerListener.updateLog( this.getClassName().append("服务停止监听........").toString() ); 
			ds = null ;
			serverSocket = null ;
		}else{
			//serverManagerListener.updateLog( this.getClassName().append("服务未启动监听......").toString() ); 
		}
		
    }
	
    /** 
     * 将响应包发送给请求端 
     */  
    public void response(SocketAddress address,String info){  
        try {  
            DatagramPacket dp = new DatagramPacket(info.getBytes(), info.getBytes().length, address);  
            dp.setData(info.getBytes());  
            ds.send(dp);  
        } catch (Exception e) {  
        	//serverManagerListener.updateLog( this.getClassName().append( e.getMessage() ).toString() ) ;
        }         
    }  
	
    /**
     * 判断当前服务启动与否
     * @return
     * @author liux
     */
    public Boolean isOrStart(){
    	 //防止重复启动服务
    	Boolean bool = true ;
		if( ds == null ){
			bool = false ;
		}
		return bool ;
    }    

}

class dataSaveService implements Runnable{
	
	public static final Logger log = Logger.getLogger(dataSaveService.class);
	
	private  UdpServerSocket socket ;
	private DatagramPacket packet ;
	
	public dataSaveService( UdpServerSocket socket ,DatagramPacket packet){
		this.socket = socket ;
		this.packet = packet ;
	}
	
	@Override
	public void run() {
		PhoneMessage stu = (PhoneMessage)getObject( packet.getData() ) ;
		StringBuffer info = new StringBuffer( "接收手机号码：" );
		info.append( stu.getReceivePhone() ).append("，实际发送手机号：").append( stu.getMessage() ) ;
		log.info( info ) ;
         //接受的是纯文本消息
        // String info = new String(packet.getData(), 0, packet.getLength());  
        // socket.serverManagerListener.updateLog( socket.getClassName().append("接收信息：").append( info ).toString() ) ;  
		
	}
	
    /***
     * 将二进制转换为对象
     * @param buffer
     * @return
     * @author liux
     */
    public Object getObject(byte[] buffer) {
	    Object obj = null;
	    try {
	        ByteArrayInputStream buffers = new ByteArrayInputStream(buffer);
	        ObjectInputStream in = new ObjectInputStream(buffers);
	        obj = in.readObject();
	        in.close();
	    } catch (Exception e) {
	        System.out.println("error");
	    }
	    return obj ;
    }
	
}
