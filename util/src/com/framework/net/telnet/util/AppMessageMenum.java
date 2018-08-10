/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.util;


/**
 * @className:AppMessageMenum.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-8-5 下午4:17:31
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public enum AppMessageMenum {
	
	ONE(0, "成功"),
	SIX(6,"没有数据"),
	MINUS_ONE(-1, "发生未知错误， 一般是服务器错误引起"),
	MINUS_TWO(-2,"Body 数据解析错误"),
	MINUS_THREE(-3, "没有找到命令处理类"),
	MINUS_FOUR(-4,"请求head 参数为空"),
	appServiceException(-50,"service 异常"),
	positionServiceException(-51,"调用算法定位异常"),
	jsonToBean(-52,"json转换为Bean 错误"),
	beanToJson(-53,"bean转换为Json 错误"),
	strToJson(-54,"字符串为空 或 不是JSON 格式的字符串"),
	strToJsonParse(-55,"JSON 格式的字符串 解析成JSON 对象错误"),
	httpRequestError(-56,"Http 请求异常错误"),
	ERROR_1012(1012, "Http 请求  ClientProtocolException  异常错误"),
	ERROR_1013(1013,"Http 响应超时 异常错误"),
	ERROR_1014(1014, "Http 请求 IOException 异常错误")
	;
	
	private Integer st;
	private String msg;

	private AppMessageMenum(Integer st, String msg) {
		this.st = st;
		this.msg = msg;
	}

	public Integer getSt() {
		return st;
	}

	public void setSt(Integer st) {
		this.st = st;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
