package com.framework.encrypter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 类名称：		Base64.java 
 * 类描述：   		Base64字符编码工具类
 * 创建人：		
 * 创建时间：		2014-12-4下午2:13:48 
 * 修改人：		liuxing
 * 修改时间：		2014-12-4下午2:13:48 
 * 修改备注：   
 * @version
 */
public class Base64 {
	private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
	
	public static void main(String[] args) {
		String encodelefter = encode(new String("message,sf1231").getBytes()) ;
		System.out.println( "'message,sf1231'加密后为：" + encodelefter );
		System.out.println( "'message,sf1231'加密后为：" + encodelefter + ",再解密为：" +new String(decode(encodelefter)) ) ;
	}

	/****
	 * 对字符串进行Base64方法加密
	 * @param data	二进制数据
	 * @return		加密后的字符串
	 */
	public static String encode(byte[] data) {
		int start = 0;
		int len = data.length;
		StringBuffer buf = new StringBuffer(data.length * 3 / 2);

		int end = len - 3;
		int i = start;
		int n = 0;

		while (i <= end) {
			int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 0x0ff) << 8) | (((int) data[i + 2]) & 0x0ff);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append(legalChars[d & 63]);

			i += 3;

			if (n++ >= 14) {
				n = 0;
				buf.append(" ");
			}
		}

		if (i == start + len - 2) {
			int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 255) << 8);

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append(legalChars[(d >> 6) & 63]);
			buf.append("=");
		} else if (i == start + len - 1) {
			int d = (((int) data[i]) & 0x0ff) << 16;

			buf.append(legalChars[(d >> 18) & 63]);
			buf.append(legalChars[(d >> 12) & 63]);
			buf.append("==");
		}

		return buf.toString();
	}

	private static int decode(char c) {
		if (c >= 'A' && c <= 'Z')
			return ((int) c) - 65;
		else if (c >= 'a' && c <= 'z')
			return ((int) c) - 97 + 26;
		else if (c >= '0' && c <= '9')
			return ((int) c) - 48 + 26 + 26;
		else
			switch (c) {
			case '+':
				return 62;
			case '/':
				return 63;
			case '=':
				return 0;
			default:
				throw new RuntimeException("unexpected code: " + c);
			}
	}

	/**
	 * 基于Base64算法的解密
	 * @param encodeMessage	传入的是加密过后的字符串
	 * @return				解密后的二进制数组
	 */
	public static byte[] decode( String encodeMessage ) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			decode(encodeMessage, bos);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		byte[] decodedBytes = bos.toByteArray();
		try {
			bos.close();
			bos = null;
		} catch (IOException ex) {
			System.err.println("Error while decoding BASE64: " + ex.toString());
		}
		return decodedBytes;
	}

	private static void decode(String s, OutputStream os) throws IOException {
		int i = 0;

		int len = s.length();

		while (true) {
			while (i < len && s.charAt(i) <= ' ')
				i++;

			if (i == len)
				break;

			int tri = (decode(s.charAt(i)) << 18) + (decode(s.charAt(i + 1)) << 12) + (decode(s.charAt(i + 2)) << 6) + (decode(s.charAt(i + 3)));

			os.write((tri >> 16) & 255);
			if (s.charAt(i + 2) == '=')
				break;
			os.write((tri >> 8) & 255);
			if (s.charAt(i + 3) == '=')
				break;
			os.write(tri & 255);

			i += 4;
		}
	}
}

