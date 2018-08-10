/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.exception;

import com.framework.net.telnet.util.ResponseErrorMessage;



/**
 * @className:AppHttpRequestException.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-8-18 下午2:01:31
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class AppHttpRequestException extends Exception {

	private static final long serialVersionUID = -1529963345102281490L;
	private ResponseErrorMessage appResponseMessage;

	/**
	 * Constructs .
	 */
	public AppHttpRequestException() {
		super();
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 */
	public AppHttpRequestException(String message) {
		super(message);
	}

	public AppHttpRequestException(ResponseErrorMessage appResponseMessage) {
		super();
		this.appResponseMessage = appResponseMessage;
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 * @param cause
	 */
	public AppHttpRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs
	 * 
	 * @param cause
	 */
	public AppHttpRequestException(Throwable cause) {
		super(cause);
	}

	public ResponseErrorMessage getAppResponseMessage() {
		return appResponseMessage;
	}

}
