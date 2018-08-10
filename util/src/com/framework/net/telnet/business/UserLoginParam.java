package com.framework.net.telnet.business;

import com.framework.net.telnet.dto.BaseDto;


/***
 * *
 * 类名称：		UserLoginParam.java 
 * 类描述：   		用户登录参数
 * 创建人：		
 * 创建时间：		2015-6-3下午4:01:46 
 * 修改人：		liuxing
 * 修改时间：		2015-6-3下午4:01:46 
 * 修改备注：   		
 * @version
 */
public class UserLoginParam implements BaseDto{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String n ;
	private String p ;
	
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	
	
	
	

}
