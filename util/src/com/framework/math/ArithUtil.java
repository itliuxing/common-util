package com.framework.math;

import java.math.BigDecimal;

/***
 * *
 * 类名称：		Arith   
 * 类描述：   		由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精确的浮点数运算，包括加减乘除和四舍五入
 * 创建人：		network
 * 创建时间：		2014-11-25 下午3:41:47   
 * 修改人：		liuxing
 * 修改时间：		2014-11-25 下午3:41:47   
 * 修改备注：   
 * @version
 */
public class ArithUtil {
	
	private static final int DEF_DIV_SCALE = 4; // 默认除法运算精度

	/**
	 * 加法
	 */
	public static double add(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 减法
	 */
	public static double jian(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 乘法
	 */
	public static double cheng(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 除法运算。当发生除不尽的情况时，精确到小数点以后4位，以后的数字四舍五入
	 */
	public static double chu(String v1, String v2) {
		return chu(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
	 * 
	 * @param scale 表示需要精确到小数点以后几位
	 */
	public static double chu(String v1, String v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}

		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 四舍五入。小数点后保留2位
	 */
	public static double math45(double d) {
		return math45(String.valueOf(d));
	}
	
	/**
	 * 四舍五入,保存小數點后指定位數
	 * @param d			数据值
	 * @param digit		位數
	 * @return
	 */
	public static double math45(double d,int digit) {
		if( digit < 0){
			digit = 0 ;
		}
		return math45( String.valueOf(d) ,digit );
	}

	/**
	 * 四舍五入。小数点后保留2位
	 */
	public static double math45(String v) {
		return math45(v, 2);
	}
	
	/**
	 * 四舍五入
	 * 
	 * @param scale 小数点后保留几位
	 */
	public static double math45(String v, int scale) {
		BigDecimal b = new BigDecimal(v);
		BigDecimal one = new BigDecimal("1");

		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static void main(String[] args) {
		BigDecimal b = new BigDecimal(100.33333);
		BigDecimal one = new BigDecimal(200.44444444);
		System.out.println(b.add(one));
		System.out.println(b.add(one).setScale(2, BigDecimal.ROUND_HALF_UP));
		System.out.println( math45(213.32423, 2) );
	}
}
