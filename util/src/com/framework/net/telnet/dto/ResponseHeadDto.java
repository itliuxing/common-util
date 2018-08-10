/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.dto;

import java.io.Serializable;

/**
 * @className:AppResponseHead.java
 * @classDescription: 响应Head
 * @author: hugx
 * @createTime:2014-8-5 下午2:03:04
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class ResponseHeadDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6003869020954470483L;
	private Integer st; 	// 响应结果	--- 0:成功；1：失败
	private String msg; 	// 响应结果消息
	private Integer cmd; 	// 请求命令字
	
	public ResponseHeadDto(){}

	public ResponseHeadDto(Integer st, String msg) {
		super();
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

	public Integer getCmd() {
		return cmd;
	}

	public void setCmd(Integer cmd) {
		this.cmd = cmd;
	}

}
