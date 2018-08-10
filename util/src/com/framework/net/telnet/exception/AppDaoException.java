/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.exception;

import com.framework.net.telnet.util.ResponseErrorMessage;



/**
 * @className:AppDaoException.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-8-12 下午4:06:07
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class AppDaoException extends Exception {
	private static final long serialVersionUID = 8867455999838301497L;
	private ResponseErrorMessage appErrorMessage;

	/**
	 * Constructs .
	 */
	public AppDaoException() {
		super();
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 */
	public AppDaoException(String message) {
		super(message);
	}

	public AppDaoException(ResponseErrorMessage appErrorMessage) {
		super();
		this.appErrorMessage = appErrorMessage;
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 * @param cause
	 */
	public AppDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs
	 * 
	 * @param cause
	 */
	public AppDaoException(Throwable cause) {
		super(cause);
	}

	public ResponseErrorMessage getAppErrorMessage() {
		return appErrorMessage;
	}

}
