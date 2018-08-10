package com.framework.http.getpost;

/**
 * @Class 	ResultEntity.java
 * @Author 	作者姓名:刘兴 
 * @Version	1.0
 * @Date	创建时间：2018-2-28 下午14:07:50
 * @Copyright Copyright by 智多星
 * @Direction 类说明   通知后的返回值
 */
public class ResultEntity {
	
	private Integer status ;
	private String data ;
	private String message ;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
