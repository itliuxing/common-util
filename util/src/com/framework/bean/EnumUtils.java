package com.framework.bean;

/**
 * 类名称：		EnumUtils.java 
 * 类描述：		针对enum对象的操作
 * 创建人：		
 * 创建时间：		2014-12-4下午1:51:56 
 * 修改人：		liuxing
 * 修改时间：		2014-12-4下午1:51:56 
 * 修改备注：   
 * @version
 */
public abstract class EnumUtils {

	/**
	 * 从指定的枚举类根据名称匹配指定枚举实例
	 * @param <T>
	 * @param enumClass			枚举类型
	 * @param constantName		枚举名称
	 * @return					枚举实例,枚举名称不存在则返回 null
	 */
    public static <T extends Enum<T>> T fromEnumConstantName(Class<T> enumClass, String constantName) {
        T[] enumConstants = enumClass.getEnumConstants();
        for (T t : enumConstants) {
            if (((Enum<?>) t).name().equals(constantName)) {
                return t;
            }
        }
        return null;
    }
    
}
