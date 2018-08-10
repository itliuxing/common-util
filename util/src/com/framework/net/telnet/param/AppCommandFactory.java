/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.param;

import com.framework.net.telnet.exception.AppCommandFactoryException;
import com.framework.net.telnet.util.AppMessageMenum;
import com.framework.net.telnet.util.ResponseErrorMessage;
import com.framework.string.StringUtils;



/**
 * *
 * 类名称：		AppCommandFactory.java 
 * 类描述：   		根据spring的映射获取内部Bean
 * 创建人：		
 * 创建时间：		2016-10-11下午4:39:58 
 * 修改人：		liuxing
 * 修改时间：		2016-10-11下午4:39:58 
 * 修改备注：   		
 * @version
 */
public class AppCommandFactory {
	
	//public static ApplicationContext applicationContext;
	
	public static AppCommand createAppCommand(Integer cmd)throws AppCommandFactoryException{
		
		/*if(applicationContext == null){
			initApplicationContext();
		}
		
		String commandName=AppCommandPropertiesUtils.getInstance().getKey(cmd);
		if(StringUtils.isEmpty(commandName)){
			 错误码ST = -3:没有找到命令处理类 
			throw new AppCommandFactoryException(new ResponseErrorMessage(AppMessageMenum.MINUS_THREE.getSt(), AppMessageMenum.MINUS_THREE.getMsg()));
		}
		*/
		//return (AppCommand) applicationContext.getBean(commandName);
		return null ;
	}
	
	private static void initApplicationContext(){
		//applicationContext = new ClassPathXmlApplicationContext(new String[] {"classpath:spring.xml","classpath:spring-mybatis.xml"});
	}

}

