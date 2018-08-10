/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.dto;


/**
 * @className:SocketAppReqParam.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-11-12 下午2:46:58
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class SocketRequestDto implements BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8475619820432830739L;

	private RequestHeadDto requestHeadDto;

	private BaseDto requestBodyDto;

	public SocketRequestDto(RequestHeadDto requestHeadDto, BaseDto requestBodyDto) {
		super();
		this.requestHeadDto = requestHeadDto;
		this.requestBodyDto = requestBodyDto;
	}

	public RequestHeadDto getRequestHeadDto() {
		return requestHeadDto;
	}

	public BaseDto getRequestBodyDto() {
		return requestBodyDto;
	}

}
