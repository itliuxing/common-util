package com.framework.net.telnet.exception;

import com.framework.net.telnet.util.ResponseErrorMessage;

/**
 * AppHttpParseException 异常类
 * 
 * @author waterborn
 */
public class AppHttpParseHeadException extends Exception {

	private static final long serialVersionUID = -5229914743408864700L;
	private ResponseErrorMessage appResponseMessage;

	/**
	 * Constructs .
	 */
	public AppHttpParseHeadException() {
		super();
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 */
	public AppHttpParseHeadException(String message) {
		super(message);
	}

	public AppHttpParseHeadException(ResponseErrorMessage appResponseMessage) {
		super();
		this.appResponseMessage = appResponseMessage;
	}

	/**
	 * Constructs
	 * 
	 * @param message
	 * @param cause
	 */
	public AppHttpParseHeadException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs
	 * 
	 * @param cause
	 */
	public AppHttpParseHeadException(Throwable cause) {
		super(cause);
	}

	public ResponseErrorMessage getAppResponseMessage() {
		return appResponseMessage;
	}

}
