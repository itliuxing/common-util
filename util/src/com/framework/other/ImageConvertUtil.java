package com.framework.other;

import java.io.File;

/**
 * *
 * 类名称：		ImageConvertUtil.java 
 * 类描述：   		批量把一个目录下原始文件，转换成某个尺寸的图片
 * 创建人：		
 * 创建时间：		2014-12-5上午11:54:56 
 * 修改人：		liuxing
 * 修改时间：		2014-12-5上午11:54:56 
 * 修改备注：   
 * @version
 */
public class ImageConvertUtil {

	/***
	 * 具体转换工具
	 * @param file		图片文件
	 * @param width		装换后的宽度(像素)
	 * @param height	装换后的高度(像素)
	 * @throws Exception
	 */
	private static void convertPictureFile(File file, int width, int height)
			throws Exception {
		String fileNameWithEx = file.getName();
		if ( fileNameWithEx != null && fileNameWithEx.endsWith(".jpg") ) { 
			String originalName = fileNameWithEx.substring(0, fileNameWithEx.lastIndexOf("."));
			File dir = file.getParentFile();
			String convertName = originalName + "_" + width + "x" + height + ".jpg";
			ImageConvertHelper.convertImage(file, dir, convertName, width,height);
		}
	}

	/***
	 * 图像分辨率转换
	 * @param dir		文件路径
	 * @param width		图像长度
	 * @param height	图像高度
	 * @throws Exception
	 */
	public static void convertPictureDir(String dir, int width, int height)
			throws Exception {
		File imageDir = new File(dir);
		if (!imageDir.exists()) {
			System.err.println("Error! Original picture directory is not existing!");
			return;
		}
		if (!imageDir.isDirectory()) {
			convertPictureFile(imageDir, width, height);
		} else {
			File[] fileList = imageDir.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				convertPictureDir(fileList[i].getAbsolutePath(), width, height);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String argss[] = {"D:\\img","30","20"} ;
		if (argss.length != 3) {
			System.out.println("args must be 3");
		} else {
			convertPictureDir(argss[0], Integer.parseInt(argss[1]), Integer.parseInt(argss[2]));
		}
	}
}
