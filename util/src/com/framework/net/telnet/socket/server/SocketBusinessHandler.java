/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.socket.server;

import org.apache.log4j.Logger;

import com.framework.net.telnet.business.RealBusinessHandler;
import com.framework.net.telnet.dto.BaseDto;
import com.framework.net.telnet.dto.RequestHeadDto;
import com.framework.net.telnet.dto.SocketRequestDto;
import com.framework.net.telnet.dto.SocketResponseDto;
import com.framework.net.telnet.exception.AppCommandException;
import com.framework.net.telnet.exception.AppHttpParseBodyException;
import com.framework.net.telnet.exception.JsonAndBeanSwitchException;
import com.framework.net.telnet.param.AppCommand;
import com.framework.net.telnet.param.AppCommandFactory;
import com.framework.net.telnet.util.AppMessageMenum;
import com.framework.net.telnet.util.AppWrapResponseUtil;
import com.framework.net.telnet.util.ResponseErrorMessage;

/**
 * *
 * 类名称：		SocketAppHandler.java 
 * 类描述：   		每次数据转发过来后，真实业务数据转发到实际处理类的业务
 * 创建人：		
 * 创建时间：		2016-10-11下午5:16:34 
 * 修改人：		liuxing
 * 修改时间：		2016-10-11下午5:16:34 
 * 修改备注：   		
 * @version
 */
public class SocketBusinessHandler {
	private static Logger log = Logger.getLogger(SocketBusinessHandler.class);

	private static SocketBusinessHandler instance;

	private SocketBusinessHandler() {
		super();
	}

	public synchronized static SocketBusinessHandler getInstance() {
		if (instance == null) {
			instance = new SocketBusinessHandler();
		}

		return instance;
	}

	/**
	 * 处理请求
	 * @param headParam
	 *            请求head 参数
	 * @param bodyParam
	 *            请求body 参数
	 * @return
	 * @throws AppWrapResponseException
	 */
	public static SocketResponseDto handleRequest(SocketRequestDto socketRequestDto) {

		RequestHeadDto requestHeadDto =null;
		SocketResponseDto socketResponseDto =null;
		try {
			//判断已经解析的传输数据是否满足要求
			if (socketRequestDto == null) {
				ResponseErrorMessage appResponseMessage = new ResponseErrorMessage(AppMessageMenum.MINUS_FOUR.getSt(), AppMessageMenum.MINUS_FOUR.getMsg());
				socketResponseDto =AppWrapResponseUtil.wrapResponse(appResponseMessage, null);
				return socketResponseDto;
			}

			requestHeadDto = socketRequestDto.getRequestHeadDto();
			//将已经编码好的数据，交给适配器做处理，然后生成实际处理类
			AppCommand appCommand = requestHeadDto.getCmd().equals( 1 ) ? new RealBusinessHandler() : null ;  //AppCommandFactory.createAppCommand(requestHeadDto.getCmd());
			//再调用实际处理类的业务过程，业务过程处理后返回一个标准的返回值
			socketResponseDto = receiveCommand(appCommand, socketRequestDto.getRequestBodyDto(), requestHeadDto);
		} catch (Exception e) {
			log.error( "服务处理异常." ) ;
		}
		if(requestHeadDto !=null){
			//返回的数据里面也返回这个标准数据
			socketResponseDto.getResponseHeadDto().setCmd(requestHeadDto.getCmd());
		}
		return socketResponseDto;

	}

	/**
	 * 调用实力业务处理类的业务逻辑
	 * @param appRequestHead
	 *            TODO
	 * @throws AppCommandException
	 * @throws JsonAndBeanSwitchException
	 * @throws AppHttpParseBodyException
	 */
	private static SocketResponseDto receiveCommand(AppCommand appCommand, BaseDto requestBodyDto, RequestHeadDto appRequestHead) throws AppCommandException,
			AppHttpParseBodyException, JsonAndBeanSwitchException {
		return (SocketResponseDto) appCommand.receiveCommand(requestBodyDto, appRequestHead, true);
	}

}
