package com.framework.other;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/***
 * *
 * 类名称：		DecodeUtil.java 
 * 类描述：   		字符编码转换
 * 创建人：		
 * 创建时间：		2016-10-11上午9:42:53 
 * 修改人：		liuxing
 * 修改时间：		2016-10-11上午9:42:53 
 * 修改备注：   		
 * @version
 */
public class DecodeUtil {

	private static final Logger logger = Logger.getLogger(DecodeUtil.class);
	/* 使用Unicode编码方式 */
	public static final String ENCODEING = "UTF-16BE";

	// 二进制转字符串
	public static String byte2hex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		String tmp = "";
		for (int i = 0; i < b.length; i++) {
			tmp = Integer.toHexString(b[i] & 0XFF);
			if (tmp.length() == 1) {
				sb.append("0" + tmp);
			} else {
				sb.append(tmp);
			}

		}

		return sb.toString();
	}

	// 转化十六进制编码为字符串
	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
	
	

	/**
	 * 二进制 转换为 十六进制字符串 码
	 */
	public static String byteToHex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		String tmp = "";
		for (int i = 0; i < b.length; i++) {
			tmp = Integer.toHexString(b[i] & 0XFF);
			if (tmp.length() == 1) {
				sb.append("0" + tmp);
			} else {
				sb.append(tmp);
			}

		}

		return sb.toString().toUpperCase();

	}

	/**
	 * 二进制 转换为 ASCII 码
	 */
	public static String byteToASCII(byte[] b) {
		return hexToASCII(byteToHex(b));
	}

	/**
	 * 十六进制 字符串 转换为 ASCII码字符串 ,使用指定编码方式，如：Unicode编码方式
	 * 
	 * @param s
	 *            十六进制 字符串
	 * @return ASCII 字符串
	 */
	public static String hexToASCII(String s) {
		return hexToASCII(s, true);
	}

	/**
	 * 十六进制 字符串 转换为 ASCII码字符串
	 * 
	 * @param s
	 *            十六进制 字符串
	 * @param flag
	 *            true 表示 使用指定编码方式，如：Unicode编码方式； false 表示采用系统默认编码方式
	 * @return ASCII 字符串
	 */
	public static String hexToASCII(String s, boolean flag) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			if (flag) {
				s = new String(baKeyword, ENCODEING); /* 使用Unicode编码方式 */
			} else {
				s = new String(baKeyword); /* 使用Unicode编码方式 */
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	public static char convertIntToAscii(int a) {
		return (a >= 0 && a <= 255) ? (char) a : '\0';
	}

	public static void main(String[] args) {
		String str="SerialNum:123;Command:12;reverse:false;battery:3.8;csq:14906;Fenable:false;posX:0;posY:0;";
		String str3="SerialNum:1;Command:02;ReportStart:0;ReportInterval:1;Fenable:1;";
		String str5="001122"; //"00112200001100000002";
		String ssd5=toStringHex(str5);
		String u16 = "7B 22 74 79 70 65 22 3A 22 31 22 2C 22 6E 61 6D 65 22 3A 22 67 6D 22 2C 22 64 61 74 61 22 3A 7B 22 64 61 74 61 74 79 70 65 22 3A 22 72 66 22 2C 22 6E 61 6D 65 22 3A 22 72 66 22 2C 22 69 64 22 3A 22 31 30 30 30 22 7D 7D" ;
		//System.out.println( hexToASCII(u16) ) ;
		String json1 = "{\"type\":\"1\",\"name\":\"gm\",\"data\":{\"datatype\":\"rf\",\"name\":\"rf\",\"id\":\"1001\"}}";    
		System.out.println( encode(json1) );
		System.out.println( decode(encode(json1)) );
		
		System.out.println( decode( "ACED0005" ) );
	}

	/*
	 * 16进制数字字符集
	 */
	private static String hexString="0123456789ABCDEF";
	
	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encode(String str)	{
		//根据默认编码获取字节数组
		byte[] bytes=str.getBytes();
		StringBuilder sb=new StringBuilder(bytes.length*2);
		//将字节数组中每个字节拆解成2位16进制整数
		for(int i=0;i<bytes.length;i++)
		{
		sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
		sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
		}
		return sb.toString();
	}
	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String decode(String bytes){
		bytes.replace(" ", "") ;
		ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);
		//将每2位16进制整数组装成一个字节
		for(int i=0;i<bytes.length();i+=2)
			baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1))));
		return new String(baos.toByteArray());
	} 
	
	public static String replaceBlank(String str) {
			String dest = "";
				if (str!=null) {
					Pattern p = Pattern.compile("\\s*|\t|\r|\n");
					Matcher m = p.matcher(str);
					dest = m.replaceAll("");
			}
			return dest;
	}
	
	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF, 0xD9}
	 * @param src  String
	 * @return byte[]
	 */
	public static byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[8];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < 8; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 * @param src0  byte
	 * @param src1  byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}
}
