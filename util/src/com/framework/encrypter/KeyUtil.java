package com.framework.encrypter;

import java.util.UUID;

/****
 * *
 * 类名称：		KeyUtil   
 * 类描述：   		UUID生成
 * 创建人：		
 * 创建时间：		2014-11-25 下午2:02:15   
 * 修改人：		liuxing   编撰
 * 修改时间：		2014-11-25 下午2:02:15   
 * 修改备注：   
 * @version
 * source		four.common.KeyUtil
 */
public class KeyUtil {
	
	/**
	 * 生成 32 位的 UUID 字符串
	 */
	public static String genUUID32() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	/**
	 * 生成 24 位的 UUID 字符串
	 */
	public static String genUUID24() {
		String s = genUUID32();
		return s.substring(8);
	}
	
	
}
