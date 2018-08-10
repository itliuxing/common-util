/**
 * Copyright (C) 2014 上海高恒通信技术有限公司
 *  @version 1.0
 */
package com.framework.net.telnet.util;

import java.lang.reflect.Type;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.framework.net.telnet.exception.JsonAndBeanSwitchException;
import com.framework.string.StringUtils;


/**
 * json 与 javaBean 的转换工具
 * 
 * @author hugx
 * 
 */
public class JsonAndBeanSwitchUtil {

	/**
	 * json 对象 转换为JavaBean 对象
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @param t
	 *            JavaBean类
	 * @return
	 */
	public static <T> T jsonToBean(String jsonStr, Type t) throws JsonAndBeanSwitchException {
		try {
			return JSON.parseObject(jsonStr, t);
		} catch (Exception e) {
			throw new JsonAndBeanSwitchException(new ResponseErrorMessage(AppMessageMenum.jsonToBean.getSt(), AppMessageMenum.jsonToBean.getMsg()));
		}
	}

	public static <T> String beanToJson(T t) throws JsonAndBeanSwitchException {
		try {
			return JSON.toJSONString(t);
		} catch (Exception e) {
			throw new JsonAndBeanSwitchException(new ResponseErrorMessage(AppMessageMenum.beanToJson.getSt(), AppMessageMenum.beanToJson.getMsg()));
		}
	}

	/**
	 * json 字符串 解析为json对象
	 * 
	 * @param jsonStr
	 * @return
	 * @throws JsonAndBeanSwitchException
	 */
	public static JSONObject jsonStrToJson(String jsonStr) throws JsonAndBeanSwitchException {
		if (!StringUtils.isJsonStr(jsonStr) ) {
			throw new JsonAndBeanSwitchException(new ResponseErrorMessage(AppMessageMenum.strToJson.getSt(), AppMessageMenum.strToJson.getMsg()));
		}

		JSONObject json = null;
		try {
			json = JSONObject.parseObject(jsonStr);
		} catch (Exception e) {
			throw new JsonAndBeanSwitchException(new ResponseErrorMessage(AppMessageMenum.strToJsonParse.getSt(), AppMessageMenum.strToJsonParse.getMsg()));
		}

		return json;
	}
}
