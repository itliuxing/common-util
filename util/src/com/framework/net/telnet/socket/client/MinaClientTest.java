package com.framework.net.telnet.socket.client;

import com.alibaba.fastjson.JSONObject;
import com.framework.net.telnet.business.UserLoginParam;
import com.framework.net.telnet.callback.CallbackI;
import com.framework.net.telnet.dto.RequestHeadDto;
import com.framework.net.telnet.dto.SocketRequestDto;
import com.framework.net.telnet.dto.SocketResponseDto;

/****
 * *
 * 类名称：		MinaClientTest.java 
 * 类描述：   		
 * 创建人：		
 * 创建时间：		2016-10-13下午3:52:42 
 * 修改人：		liuxing
 * 修改时间：		2016-10-13下午3:52:42 
 * 修改备注：   		
 * @version
 */
public class MinaClientTest implements CallbackI {

	@Override
	public void callback(){}
	
	@Override
	public void callback( SocketResponseDto response ) {
		// sosket的回掉结果
		System.out.println(" 请求得到结果后，回调结果...... " ) ;
	    System.out.println("Server Say: message :" + JSONObject.toJSONString( response ) );  
	}
	
	public static void main(String[] args) {
		//client 测试类
		MinaClientTest minaClientTest = new MinaClientTest() ;
		
		SocketRequestDto socketRequestDto = init() ;
		//初始化socket请求数据
		MinaClient.getInstance();
		//调用socket，并传入
		MinaClient.createSocket( minaClientTest , socketRequestDto ) ;
	}
	
	/***
	 * 要发送的数据
	 * @return
	 */
	public static SocketRequestDto init(){
		 RequestHeadDto headDto = new RequestHeadDto() ;
		  headDto.setCmd( 1 ) ;
		  UserLoginParam bodyDto = new UserLoginParam() ;		//每个业务前后端均采用一套数据结构
		  bodyDto.setN("liuxing") ;
		  bodyDto.setP("this is pass") ;
		  return new SocketRequestDto( headDto , bodyDto ) ;
	}
	

}
