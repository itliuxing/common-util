/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.exception;

import com.framework.net.telnet.util.ResponseErrorMessage;

/**
 * @className:AppCommandFactoryException.java
 * @classDescription:
 * @author: hugx
 * @createTime:6 Aug 2014 17:31:55
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class AppCommandFactoryException extends Exception {
	private static final long serialVersionUID = 6561803031507478128L;
	private ResponseErrorMessage appResponseMessage;

	/**
	 * Constructs .
	 */
	public AppCommandFactoryException() {
		super();
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 */
	public AppCommandFactoryException(String message) {
		super(message);
	}

	public AppCommandFactoryException(ResponseErrorMessage appResponseMessage) {
		super();
		this.appResponseMessage = appResponseMessage;
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 * @param cause
	 */
	public AppCommandFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs
	 * 
	 * @param cause
	 */
	public AppCommandFactoryException(Throwable cause) {
		super(cause);
	}

	public ResponseErrorMessage getAppResponseMessage() {
		return appResponseMessage;
	}
}
