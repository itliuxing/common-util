package com.framework.net.telnet.util;


/**
 * *
 * 类名称：		ResponseErrorMessage.java 
 * 类描述：   		返回信息模版类
 * 创建人：		
 * 创建时间：		2016-11-16下午5:24:25 
 * 修改人：		liuxing
 * 修改时间：		2016-11-16下午5:24:25 
 * 修改备注：   		
 * @version
 */
public class ResponseErrorMessage {
	private Integer st;
	private String msg;

	public ResponseErrorMessage(Integer st, String msg) {
		super();
		this.st = st;
		this.msg = msg;
	}

	public Integer getSt() {
		return st;
	}

	public String getMsg() {
		return msg;
	}

}
