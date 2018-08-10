package com.framework.other;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * *
 * 类名称：		ImageConvertHelper.java 
 * 类描述：   		图片转换工具类的底层实现
 * 创建人：		
 * 创建时间：		2014-12-5下午12:00:05 
 * 修改人：		liuxing
 * 修改时间：		2014-12-5下午12:00:05 
 * 修改备注：   
 * @version
 */
public class ImageConvertHelper {
	private static int[] resizeFix(int width, int height, int w, int h) {
		if (width <= w && height <= h) {
			return new int[] { width, height };
		}
		if (width * 1.0 / height > (w * 1.0 / h)) {
			h = (int) (height * w / width);
		} else {
			w = (int) (width * h / height);
		}
		return new int[] { w, h };
	}

	public static void convertImage(File ori, File dir, String smallName,
			int w, int h) throws Exception {
		Graphics2D g = null;
		FileOutputStream fos = null;
		BufferedOutputStream bout = null;
		try {
			Image image = ImageIO.read(ori);
			// 若imp为空，则另外生成错误的图片
			if (image == null) {
				throw new Exception("========Not a Image File========");
			}
			// 获得imageProcessor
			int width = image.getWidth(null);
			int height = image.getHeight(null);

			int[] smallRect = resizeFix(width, height, w, h);

			w = smallRect[0];
			h = smallRect[1];

			if (!dir.isDirectory()) {
				dir.mkdirs();
			}
			String strPath = dir.getAbsolutePath() + File.separator +

			smallName;

			fos = new FileOutputStream(strPath);
			bout = new BufferedOutputStream(fos);

			BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
			bi.getGraphics().drawImage(image.getScaledInstance(w, h,  Image.SCALE_SMOOTH), 0, 0,  null);
			
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bout);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
			param.setQuality(0.8f, true);
			encoder.encode(bi, param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (g != null) {
				g.dispose();
			}
			if (bout != null) {
				bout.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
}
