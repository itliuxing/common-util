/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.dto;

import java.io.Serializable;

/**
 * @className:RequestHeadDto.java
 * @classDescription:
 * @author: hugx
 * @createTime:2014-7-30 下午1:54:18
 * @updateAuthor:
 * @updateTime:
 * @updateDescription:
 * @version V1.0
 */
public class RequestHeadDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3378015428195849651L;
	private String aid; // 手机应用ID 可选项
	private String ver; // 当前应用版本 必选项
	private String lang; // 请求语言 必选项 默认值：zh_CN ,Locale.getDefault()
	private String sid; // 手机硬件唯一ID（MAC地址）， 可选 项
	private String mos; // 手机操作系统 必选项
	private String mod; // 手机型号 必选项
	private String de; // 提交数据时间 必选项
	private Integer sync; // 请求方式 0:同步，1：异步 （默认值为0） 必选项
	private String uuid; // 用户ID 可选项
	private Integer cmd; // 命令字 必选项
	private String serialNumber; // 请求序列号
	
	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getLang() {
		if (lang == null || lang.length() == 0) {
			lang = "zh_CN";
		}
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getMos() {
		return mos;
	}

	public void setMos(String mos) {
		this.mos = mos;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public Integer getSync() {
		if (sync == null) {
			sync = 0;
		}
		return sync;
	}

	public void setSync(Integer sync) {
		this.sync = sync;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getCmd() {
		return cmd;
	}

	public void setCmd(Integer cmd) {
		this.cmd = cmd;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	

}
