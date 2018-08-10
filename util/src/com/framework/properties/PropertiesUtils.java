package com.framework.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.framework.string.StringUtils;
import com.framework.thread.CustomExecutorService;

/**
 * *
 * 类名称：		PropertiesUtils.java 
 * 类描述：   		属性文件工具类，本工具类从指定文件的配置信息中根据传入的KEY 值获取对应的VALUE 值
 * 创建人：		
 * 创建时间：		2015-9-8上午10:22:52 
 * 修改人：		liuxing
 * 修改时间：		2015-9-8上午10:22:52 
 * 修改备注：   		由于一个项目可能又多个配置文件，为了辨别每个不同的配置文件只加载一次，将使用享元模式 确保每一个对象在整个项目只存在一份，提高内存的使用效率
 * @version
 */
public class PropertiesUtils {
	
	private static Logger log = Logger.getLogger( PropertiesUtils.class ) ;

	private Properties props = null;

	public PropertiesUtils( String propertiesFile ) {
		init( propertiesFile );
	}

	/***
	 * 读取单个属性文件信息
	 * @param key
	 * @return
	 */
	public String getKey(String key) {
		if (StringUtils.isEmpty(key)) {
			return null;
		}
		return props.getProperty(key);
	}

	/***
	 * 读取配置文件信息
	 * @param propertiesFile
	 */
	private void init(  String propertiesFile  ) {
		try {
			props = new Properties();
			InputStream fis = new BufferedInputStream( new FileInputStream( this.getClass().getResource("/").getPath() + propertiesFile ) );
			props.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("配置文件不存在，请将配置文件放置src目录下，并将放置位置传入：error info " + e.getMessage() );
		}

	}

}
