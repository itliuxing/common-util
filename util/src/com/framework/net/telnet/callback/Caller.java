package com.framework.net.telnet.callback;

/****
 * *
 * 类名称：		Caller.java 
 * 类描述：   		调度器
 * 创建人：		
 * 创建时间：		2016-10-13下午3:42:33 
 * 修改人：		liuxing
 * 修改时间：		2016-10-13下午3:42:33 
 * 修改备注：   		
 * @version
 */
public class Caller {

	private CallbackI callback; // 私有接口成员

	public void setCallback(CallbackI callback) {
		this.callback = callback; // 接口成员的实现：从外部传入
		doCallback() ;
	}

	public void doCallback() { // 回调接口成员的方法
		callback.callback();
	}
}
