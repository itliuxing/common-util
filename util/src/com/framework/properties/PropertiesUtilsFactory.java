package com.framework.properties;

import java.util.HashMap;
import java.util.Map;

/**
 * *
 * 类名称：		PropertiesUtils.java 
 * 类描述：   		属性文件工具类，本工具类从指定文件的配置信息中根据传入的KEY 值获取对应的VALUE 值
 * 创建人：		
 * 创建时间：		2015-9-8上午10:22:52 
 * 修改人：		liuxing
 * 修改时间：		2015-9-8上午10:22:52 
 * 修改备注：   		享元模式 确保每一个对象在整个项目的每一个属性读取对象只存在一份，提高内存的使用效率
 * @version
 */
public class PropertiesUtilsFactory {
	
	public PropertiesUtilsFactory(){}
	
	private static PropertiesUtilsFactory propertiesUtilsFactory ;
	
	/***
	 * 在享元模式中最需要注意的就是，享元模式的主入口 必须是单例，否则享元主入口存在多分，那么子类也会存在多份
	 * @return
	 */
	public static PropertiesUtilsFactory getInstace(){
		if( propertiesUtilsFactory == null ){
			propertiesUtilsFactory = new PropertiesUtilsFactory() ;
		}
		return propertiesUtilsFactory ;
	}

	private Map<String, PropertiesUtils> propertiesMap = new HashMap<String, PropertiesUtils>();

	public PropertiesUtils factory(String state) {
		// 先从缓存中查找对象
		PropertiesUtils property = propertiesMap.get(state);
		if (property == null) {
			// 如果对象不存在则创建一个新的PropertiesUtils对象
			property = new PropertiesUtils(state);
			// 把这个新的PropertiesUtils对象添加到缓存中
			propertiesMap.put(state, property);
		}
		return property;
	}

}
