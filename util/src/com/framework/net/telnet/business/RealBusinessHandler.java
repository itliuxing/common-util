package com.framework.net.telnet.business;

import org.apache.log4j.Logger;

import com.framework.net.telnet.dto.BaseDto;
import com.framework.net.telnet.dto.ResponseHeadDto;
import com.framework.net.telnet.dto.SocketResponseDto;
import com.framework.net.telnet.exception.AppCommandException;
import com.framework.net.telnet.exception.AppHttpParseBodyException;
import com.framework.net.telnet.exception.JsonAndBeanSwitchException;
import com.framework.net.telnet.param.AppCommand;
import com.framework.net.telnet.socket.server.SocketBusinessHandler;
import com.framework.net.telnet.util.ResponseErrorMessage;

/****
 * *
 * 类名称：		RealBusinessHandler.java 
 * 类描述：   		真实业务处理类
 * 创建人：		
 * 创建时间：		2016-10-11下午5:07:04 
 * 修改人：		liuxing
 * 修改时间：		2016-10-11下午5:07:04 
 * 修改备注：   		
 * @version
 */
public class RealBusinessHandler extends AppCommand<UserLoginParam> {
	
	private static Logger log = Logger.getLogger(SocketBusinessHandler.class);

	@Override
	protected BaseDto execCommand() throws AppCommandException,
			AppHttpParseBodyException, JsonAndBeanSwitchException {
		UserLoginParam param = (UserLoginParam) requestParam ;
		log.info( " 获取到的用户名： " + param.getN() + "  密码是：" +  param.getP() ) ;
		
		return new SocketResponseDto( new ResponseHeadDto( 0,"操作成功" ) , null );
	}

	@Override
	protected void setAppReqParamClass() {
		// TODO Auto-generated method stub
		
	}

}
