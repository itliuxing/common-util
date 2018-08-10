package com.framework.math;

import java.math.BigDecimal;
import java.util.Random;

import com.framework.string.StringUtils;


/**
 * *
 * 类名称：		NumberUtil.java 
 * 类描述：   		数字型数据工具类
 * 创建人：		
 * 创建时间：		2014-12-5上午11:35:02 
 * 修改人：		liuxing
 * 修改时间：		2014-12-5上午11:35:02 
 * 修改备注：   
 * @version
 */
public class NumberUtil {

	/**
	 * 判断一个字符串是不是: 非负整数
	 * @param str	字符
	 * @return
	 */
	public static boolean isDigit(String str) {
		if (!StringUtils.hasText(str)) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c < 48 || c > 57) { // '0': 48, '9': 57
				return false;
			}
		}
		return true;
	}

	/**
	 * 返回随机整数型，区间：[small , big]
	 * @param small		最小值
	 * @param big		最大值
	 * @return
	 */
	public static int randomNumber(int small, int big) {
		if (small > big) {
			small = big + (big = small) * 0; // 交换
		}
		return (new Random()).nextInt(big - small + 1) + small;
	}

	/**
	 * 把 object 转化为整数
	 * @param obj	需要转型的数据
	 * @return
	 */
	public static int parseInt(Object obj) {
		if (null == obj) {
			return -1;
		}
		String str = String.valueOf(obj);
		int num = -1;
		try {
			num = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			num = -1;
		}
		return (num < 0 ? -1 : num);
	}

	/**
	 * 把 object 转化为 long
	 * @param obj	需要转型的数据
	 * @return
	 */
	public static long parseLong(Object obj) {
		if (null == obj) {
			return -1L;
		}
		String str = String.valueOf(obj);
		long num = -1L;
		try {
			num = Long.parseLong(str);
		} catch (NumberFormatException e) {
			num = -1;
		}
		return (num < 0 ? -1L : num);
	}

	/**
	 * 把 object 转化为 BigDecimal
	 * @param obj	需要转型的数据
	 * @return
	 */
	public static BigDecimal parseBigDecimal(Object obj) {
		double num = parseDouble(obj);
		BigDecimal big = new BigDecimal(String.valueOf(num));
		return big;
	}

	/**
	 * 把 object 转化为 double
	 * @param obj	需要转型的数据
	 * @return
	 */
	public static double parseDouble(Object obj) {
		if (null == obj) {
			return -1.0;
		}
		String str = String.valueOf(obj);
		double num = -1.0;
		try {
			num = Double.parseDouble(str);
		} catch (NumberFormatException e) {
			num = -1.0;
		}
		return (num < 0 ? -1.0 : num);
	}

	/**
	 * 把 object 转化为 float
	 * @param obj	需要转型的数据
	 * @return
	 */
	public static float parseFloat(Object obj) {
		if (null == obj) {
			return -1.0F;
		}
		String str = String.valueOf(obj);
		float num = -1.0F;
		try {
			num = Float.parseFloat(str);
		} catch (NumberFormatException e) {
			num = -1.0F;
		}
		return (num < 0 ? -1.0F : num);
	}

	/**
	 * 把 Integer 转化为 Long
	 * @param num	需要转型的数据
	 * @return
	 */
	public static Long integerToLong(Integer num) {
		return (num == null) ? null : (new Long(num.longValue()));
	}

	/**
	 * 把 Float 转化为 Double
	 * @param num	需要转型的数据
	 * @return
	 */
	public static Double floatToDouble(Float num) {
		return (num == null) ? null : (new Double(num.doubleValue()));
	}

}
