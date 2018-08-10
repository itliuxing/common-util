/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.param;

import com.framework.net.telnet.dto.BaseDto;
import com.framework.net.telnet.dto.RequestHeadDto;
import com.framework.net.telnet.exception.AppCommandException;
import com.framework.net.telnet.exception.AppHttpParseBodyException;
import com.framework.net.telnet.exception.JsonAndBeanSwitchException;
import com.framework.net.telnet.util.AppMessageMenum;
import com.framework.net.telnet.util.AppParseRequestUtil;
import com.framework.net.telnet.util.AppWrapResponseUtil;
import com.framework.net.telnet.util.ResponseErrorMessage;

/**
 * @className:AppCommand.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-8-5 上午11:05:35
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 * @param <T>
 * @param <F>
 */
public abstract class AppCommand<T> {
	protected Class requestParamClass = BaseDto.class; /* 请求参数类 的class */
	protected BaseDto requestParam; /* 请求参数对象 */
	protected RequestHeadDto requestHeadDto; /* 请求head 对象 */

	/**
	 * 命令方法
	 * 
	 * @param bodyParam
	 *            请求参数
	 * @return 响应的JSON对象
	 * @throws AppCommandException
	 * @throws AppHttpParseBodyException
	 * @throws JsonAndBeanSwitchException
	 */
	protected abstract BaseDto execCommand() throws AppCommandException, AppHttpParseBodyException, JsonAndBeanSwitchException;

	/**
	 * 设置子命令的请求参数class
	 */
	protected abstract void setAppReqParamClass();

	/**
	 * 解析请求参数body
	 * 
	 * @throws AppHttpParseBodyException
	 * @throws JsonAndBeanSwitchException
	 */
	public void parseRequestBody(String bodyParam) throws AppHttpParseBodyException, JsonAndBeanSwitchException {
		requestParam = (BaseDto) AppParseRequestUtil.parseBody(bodyParam, requestParamClass);

	}

	/**
	 * 接收处理 ，模板方法
	 * 
	 * @param <T>
	 * @param bodyParam
	 * @param requestHeadDto
	 * @param correspondType
	 *            通信类型 false - http通信协议，true - socekt 通信
	 * @return
	 * @throws AppHttpParseBodyException
	 * @throws JsonAndBeanSwitchException
	 * @throws AppCommandException
	 */
	public <F> T receiveCommand(F bodyParam, RequestHeadDto requestHeadDto, boolean correspondType) throws AppHttpParseBodyException,
			JsonAndBeanSwitchException, AppCommandException {

		this.requestHeadDto = requestHeadDto;

		setAppReqParamClass();
		if (!correspondType) {
			parseRequestBody((String) bodyParam);
		}else{
			requestParam=(BaseDto) bodyParam;
		}

		BaseDto baseDto = null;
		if (requestHeadDto != null && requestHeadDto.getSync() == 1) {/* 异步处理 */
			baseDto = execAsyHandle();
		} else { /* 同步处理 */
			baseDto = execSyncHandle();
		}

		ResponseErrorMessage appResponseMessage = new ResponseErrorMessage(AppMessageMenum.ONE.getSt(), AppMessageMenum.ONE.getMsg());

		if (correspondType) {
			return (T) AppWrapResponseUtil.wrapResponse(appResponseMessage, baseDto);
		} else {

			return (T) AppWrapResponseUtil.wrapResponseToStr(appResponseMessage, baseDto);
		}

	}

	/**
	 * 执行异步处理
	 */
	private BaseDto execAsyHandle() {
		return null;
	}

	/**
	 * 执行同步处理
	 * 
	 * @throws JsonAndBeanSwitchException
	 * @throws AppHttpParseBodyException
	 * @throws AppCommandException
	 */
	private BaseDto execSyncHandle() throws AppCommandException, AppHttpParseBodyException, JsonAndBeanSwitchException {
		return execCommand();
	}

}
