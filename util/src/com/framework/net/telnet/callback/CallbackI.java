package com.framework.net.telnet.callback;

import com.framework.net.telnet.dto.SocketResponseDto;

/****
 * *
 * 类名称：		CallInterface.java 
 * 类描述：   		回调函数接口
 * 创建人：		
 * 创建时间：		2016-10-13下午3:27:41 
 * 修改人：		liuxing
 * 修改时间：		2016-10-13下午3:27:41 
 * 修改备注：   		
 * @version
 */
public interface CallbackI {

	public void callback( SocketResponseDto response );  
	
	public void callback();  
	
}
