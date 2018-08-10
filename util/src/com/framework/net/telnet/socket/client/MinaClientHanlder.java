package com.framework.net.telnet.socket.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.alibaba.fastjson.JSON;
import com.framework.net.telnet.business.UserLoginParam;
import com.framework.net.telnet.callback.CallbackI;
import com.framework.net.telnet.dto.RequestHeadDto;
import com.framework.net.telnet.dto.SocketRequestDto;
import com.framework.net.telnet.dto.SocketResponseDto;
import com.framework.net.telnet.dto.body.RequestBodyDto;

/***
 * *
 * 类名称：		MinaClientHanlder.java 
 * 类描述：   		
 * 创建人：		
 * 创建时间：		2016-9-7上午10:26:32 
 * 修改人：		liuxing
 * 修改时间：		2016-9-7上午10:26:32 
 * 修改备注：   		
 * @version
 */
public class MinaClientHanlder extends IoHandlerAdapter{  
	
	private SocketRequestDto socketRequestDto ;
	private CallbackI callback ;
	
	public MinaClientHanlder( CallbackI callback , SocketRequestDto socketRequestDto ){
		this.callback = callback ;
		this.socketRequestDto = socketRequestDto ;
	}
    
  public void sessionOpened(IoSession session) throws Exception {  
	  //SocketRequestDto socketRequestDto = new SocketRequestDto( new RequestHeadDto() , new RequestBodyDto() );  
	  //当传过来的数据为空时，在测试环境则自动赋值
	  if( socketRequestDto == null ){
		  RequestHeadDto headDto = new RequestHeadDto() ;
		  headDto.setCmd( 1 ) ;
		  UserLoginParam bodyDto = new UserLoginParam() ;		//每个业务前后端均采用一套数据结构
		  bodyDto.setN("liuxing") ;
		  bodyDto.setP("this is pass") ;	  
		  socketRequestDto = new SocketRequestDto( headDto , bodyDto );  
	  }
	  session.write( socketRequestDto );  
	  //长连接则不断在此处发声数据触发，往上层发送数据
	  System.out.println("对象变成字符是：" + JSON.toJSONString(socketRequestDto) + "。此处监听，Mina往上层写得数据是什么...");
  }
    
  @Override  
  public void messageReceived(IoSession session, Object message)  
          throws Exception {  
	  callback.callback( (SocketResponseDto) message ) ;
	  //回调流程完成后，需要继续的话走继续业务，否则关闭连接
      session.close( true ) ;		//-----短链接可以立即关闭----长连接则设定条件在关闭---此处为立即关闭
  }  
    
  @Override  
  public void sessionClosed(IoSession session) throws Exception {  
      // TODO Auto-generated method stub  
      session.close();  
  }  

}
