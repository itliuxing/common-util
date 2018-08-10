/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.dto;

/**
 * @className:ScoketResponseDto.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-11-12 下午3:02:14
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class SocketResponseDto implements BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2597103158086295765L;
	private ResponseHeadDto responseHeadDto;
	private BaseDto responseBodyDto;

	public SocketResponseDto(ResponseHeadDto responseHeadDto, BaseDto responseBodyDto) {
		super();
		this.responseHeadDto = responseHeadDto;
		this.responseBodyDto = responseBodyDto;
	}

	public ResponseHeadDto getResponseHeadDto() {
		return responseHeadDto;
	}

	public BaseDto getResponseBodyDto() {
		return responseBodyDto;
	}

}
