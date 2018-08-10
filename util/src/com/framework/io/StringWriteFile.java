package com.framework.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/****
 * *
 * 类名称：		StringWriteFile.java 
 * 类描述：   		字符保存到文件中
 * 创建人：		
 * 创建时间：		2016-10-10下午4:39:52 
 * 修改人：		liuxing
 * 修改时间：		2016-10-10下午4:39:52 
 * 修改备注：   		
 * @version
 */
public class StringWriteFile {
	
	/***
	 * 将信息写入到文件中
	 * @param file
	 * @param info
	 * @return
	 */
	public static boolean writerTo(File file, String info) {
		FileWriter filew;
		try {
			filew = new FileWriter(file); 	// 新建一个FileWriter
			filew.write(info); 				// 将字符串写入到指定的路径下的文件中
			filew.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		StringWriteFile.writerTo(new File("D:/123.txt"), "test" ) ;
	}
	
}
