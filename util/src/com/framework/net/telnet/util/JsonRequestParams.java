package com.framework.net.telnet.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonRequestParams {
	
	/**
	 * 请求参数
	 */
	private JSONObject requestJson = new JSONObject();

	/**
	 * head参数
	 */
	private JSONObject head = new JSONObject();

	/**
	 * body参数
	 */
	private JSONObject body = new JSONObject();

	public void putHead(Object value) {
		try {
			requestJson.put("head", value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加body参数
	 * 
	 * @param key
	 * @param value
	 */
	public void putBody(Object value) {
		try {
			requestJson.put("body", value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 添加body参数
	 * 
	 * @param key
	 * @param value
	 */
	public void putBody(String key, String value) {
		try {
			body.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加body参数
	 * 
	 * @param key
	 * @param value
	 */
	public void putBody(String key, Object value) {
		try {
			body.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回json字符串
	 * 
	 * @return String
	 */
	public String getRequestJson() {
		return requestJson.toString();
	}

	public JSONObject getHead() {
		return head;
	}

	public JSONObject getBody() {
		return body;
	}


}
