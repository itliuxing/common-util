package com.framework.net.telnet.callback;

import com.framework.net.telnet.dto.SocketResponseDto;

/***
 * *
 * 类名称：		ClientCallback.java 
 * 类描述：   		具体的回掉实例
 * 创建人：		
 * 创建时间：		2016-10-13下午3:28:47 
 * 修改人：		liuxing
 * 修改时间：		2016-10-13下午3:28:47 
 * 修改备注：   		
 * @version
 */
public class ClientCallback implements CallbackI {
	
	public ClientCallback(){}
	
	@Override
	public void callback(){  
       System.out.println("回调");  
    }

	@Override
	public void callback(SocketResponseDto response) {
		// TODO Auto-generated method stub
		
	}

}
