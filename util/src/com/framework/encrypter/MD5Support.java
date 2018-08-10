package com.framework.encrypter;

import java.security.MessageDigest;

/***
 * *
 * 类名称：		MD5Support   
 * 类描述：   		MD5加密
 * 创建人：		
 * 创建时间：		2014-11-26 下午5:44:56   
 * 修改人：		liuxing
 * 修改时间：		2014-11-26 下午5:44:56   
 * 修改备注：   
 * @version
 * resource		utilother.three	MD5Support
 */
public class MD5Support {
	
	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
	
	/***
	 * MD5加密--有编码要求
	 * @param info		信息
	 * @param encoding	编码
	 * @return
	 */
	public final static String MD5( String info,String encoding ) {
		return MD5Encoding( info, encoding) ;
	}
	
	/***
	 * MD5加密--默认UTF-8编码
	 * @param info		信息
	 * @return
	 */
	public final static String MD5( String info ) {
		return MD5Encoding( info,"UTF-8") ;
	}
	
	/***
	 * MD5实际的加密,私有方法  屏蔽开放的权限
	 * @param info
	 * @param encoding
	 * @return
	 */
	private final static String MD5Encoding( String info,String encoding ) {
		if( info==null ){
			return null;
		}
		try {
			//本来我要使用getBytes("UTF-8");但因为我也要在lua中使用md5,而lua中搞定utf8很麻烦，所以
			//我在此并未使用utf-8,如果所有部署的机器都是同样编码（本系统是gbk）的话，不会出问题，但如果出现不同编码的机器
			//明显会出问题
			//同样的问题也出现在base64surpport
			byte[] strTemp;
			if(encoding!=null)
				strTemp= info.getBytes(encoding);
			else
				strTemp= info.getBytes();
			//byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				//右移四位并截掉高四位
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				//直接截掉高四位
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

}
