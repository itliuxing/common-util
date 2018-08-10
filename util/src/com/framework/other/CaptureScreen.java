package com.framework.other;

import java.awt.Dimension;  
import java.awt.Rectangle;  
import java.awt.Robot;  
import java.awt.Toolkit;  
import java.awt.image.BufferedImage;  
import javax.imageio.ImageIO;  
import java.io.File; 

/**
 * @Class 	CaptureScreen.java
 * @Author 	作者姓名:刘兴 
 * @Version	1.0
 * @Date	创建时间：2017-7-21 下午3:52:42
 * @Copyright Copyright by 智多星
 * @Direction 类说明
 */

public class CaptureScreen {
	
	public static void captureScreen(String fileName) throws Exception {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		ImageIO.write(image, "png", new File(fileName));

	}

	public static void main(String[] args) {
		try {
			captureScreen( "屏幕快照.png" ) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
