package com.framework.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @Class 	HttpUtil.java
 * @Author 	作者姓名:刘兴 
 * @Version	1.0
 * @Date	创建时间：2017-7-21 下午3:46:37
 * @Copyright Copyright by 智多星
 * @Direction 类说明			直接调用网络地址获取数据
 */

public class HttpUtil {
	
	public static void main(String[] args)  {  
        try {  
            URL my_url = new URL("http://test.zhdsbang.com/bbg/");  
            BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream()));  
            String strTemp = "";  
            while(null != (strTemp = br.readLine())){  
            System.out.println(strTemp);  
        }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }  

}
