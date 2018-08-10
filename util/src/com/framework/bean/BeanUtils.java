package com.framework.bean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Class BeanUtils.java
 * @Author 作者姓名:刘兴
 * @Version 1.0
 * @Date 创建时间：2017-5-12 下午12:35:43
 * @Copyright Copyright by 智多星
 * @Direction 类说明
 */

public class BeanUtils {

	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();

		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}

}
