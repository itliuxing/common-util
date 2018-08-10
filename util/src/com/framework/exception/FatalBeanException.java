package com.framework.exception;

/***
 * *
 * 类名称：		FatalBeanException.java 
 * 类描述：   		数据从一个对象复制到另一个对象时异常
 * 创建人：		
 * 创建时间：		2016-10-10下午4:01:03 
 * 修改人：		liuxing
 * 修改时间：		2016-10-10下午4:01:03 
 * 修改备注：   		
 * @version
 */
@SuppressWarnings("serial")
public class FatalBeanException extends Exception {

	public FatalBeanException(String msg){  
        super(msg);  
    }  
	
	public FatalBeanException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FatalBeanException(Throwable cause) {
		super(cause);
	}
	
}
