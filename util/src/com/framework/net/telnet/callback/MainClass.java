package com.framework.net.telnet.callback;

/****
 * *
 * 类名称：		MainClass.java 
 * 类描述：   		
 * 创建人：		
 * 创建时间：		2016-10-13下午3:43:00 
 * 修改人：		liuxing
 * 修改时间：		2016-10-13下午3:43:00 
 * 修改备注：   		
 * @version
 */
public class MainClass {

	public MainClass() {}

	public static void main(String[] args) {
		Caller caller = new Caller();
		caller.setCallback(new ClientCallback() {@Override
			public void callback() {
				super.callback();
			}
		});
		//caller.doCallback(); // 实现回调--内部已经调用了
	}

}
