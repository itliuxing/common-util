package com.framework.http;

/**
 * @Class 	HttpProxy.java
 * @Author 	作者姓名:刘兴 
 * @Version	1.0
 * @Date	创建时间：2017-7-21 下午3:55:46
 * @Copyright Copyright by 智多星
 * @Direction 类说明
 */

public class HttpProxy {
	
	//设定代理的信息
	public void systemPropertiesSet(){
		System.getProperties().put("http.proxyHost", "someProxyURL");  
		System.getProperties().put("http.proxyPort", "someProxyPort");  
		System.getProperties().put("http.proxyUser", "someUserName");  
		System.getProperties().put("http.proxyPassword", "somePassword");
	}

}
