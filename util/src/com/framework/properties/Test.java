package com.framework.properties;

/***
 * *
 * 类名称：		Test.java 
 * 类描述：   		享元模式的实际运用以及测试
 * 创建人：		
 * 创建时间：		2015-9-8上午11:22:26 
 * 修改人：		liuxing
 * 修改时间：		2015-9-8上午11:22:26 
 * 修改备注：   		
 * @version
 */
public class Test {
	
	public static void main(String[] args) {
		//系统启动时加载的数据就可以了
		PropertiesUtils propertiesUtils = PropertiesUtilsFactory.getInstace().factory("config/config.properties") ;
		//然后就可以随地调用了
		
		System.out.println( propertiesUtils.getKey("testName1") );
		System.out.println( propertiesUtils );
		System.out.println( propertiesUtils );
		

		//通过享元模式，得到配置文件以及配置文件中属性值
		String POOL_COUNT = propertiesUtils.getKey( "testName1" ) ;
	}

}
