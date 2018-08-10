package com.framework.string;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/***
 * 类名称：		StringUtils   
 * 类描述：   		字符工具类编撰
 * 创建人：		
 * 创建时间：		2014-11-24 下午2:31:24   
 * 修改人：		liuxing   
 * 修改时间：		2014-11-24 下午2:31:24   
 * 修改备注：    		resource：one.StringToArray,four.string.SpringUtil,util.Stringutil
 * @version		1.0
 */
public class StringUtils {
	
	
	public static final int NUMERIC_SHORT = 0;
	public static final int NUMERIC_INT = 1;
	public static final int NUMERIC_LONG = 2;
	public static final int NUMERIC_FLOAT = 3;
	public static final int NUMERIC_DOUBLE = 4;
	
	/***
	 * 字符为空返回true,否则返回false
	 * @param str	判断其对象无初始化，和去掉前后空格后的长度
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/***
	 * 字符不为空返回true,否则返回false
	 * @param str 	判断其对象无初始化，和去掉前后空格后的长度
	 * @return	
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && str.trim().length() > 0;
	}
	
	/***
	 * 字符不为空返回true,否则返回false
	 * @param str 	判断其对象无初始化，和去掉前后空格后的长度
	 * @return	
	 */
	public static boolean isNotEmpty(String... str) {
		if( str == null || str.length<1 ){
			return false ;
		}
		boolean remark = false ;
		for( int i=0;i<str.length;i++ ){
			remark = isEmpty(str[i]) ;
			if( remark ){		//只要出现了有字段的空就直接返回空
				return false ;
			}
		}
		return true ;
	}
	
	/***
	 * 字符串分割
	 * @param message	需要分割的字符	传入为空时 返回一个长度为0的数组
	 * @param split		分隔符		传入为空时 返回一个长度为0的数组
	 * @return			分割后的字符串数组
	 */
	public static String[] StringToArray( String message , String split ) {
		if( isNotEmpty(message) && isNotEmpty(split) ){
			StringTokenizer stn = new StringTokenizer( message,split );
			String[] temp = new String[stn.countTokens()];
			int i = 0;
			while (stn.hasMoreTokens()) {
				temp[i] = stn.nextToken();
				i++;
			}
			return temp;
		}else{
			return new String[0];
		}
	}

	
	/***
	 * 判断字符串是否是指定类型的数据
	 * @param str		传入的字符
	 * @param intType	数据类型：this.NUMERIC_SHORT,NUMERIC_INT,NUMERIC_LONG,NUMERIC_FLOAT,NUMERIC_DOUBLE
	 * @return
	 */
	public static Object objectTo(String str,int intType){
		if ( isEmpty(str) ) {
			return null;
		}else {
			switch (intType) {
			case NUMERIC_SHORT:
				return Short.parseShort(str);
			case NUMERIC_INT:
				return Integer.parseInt(str);
			case NUMERIC_LONG:
				return Long.parseLong(str);
			case NUMERIC_FLOAT:
				return Float.parseFloat(str);
			case NUMERIC_DOUBLE:
				return Double.parseDouble(str);
			default :
				return null;
			}
		}
	}
	
	/***
	 * 判断字符串是否是指定类型的数据
	 * @param str		传入的字符
	 * @param intType	数据类型：this.NUMERIC_SHORT,NUMERIC_INT,NUMERIC_LONG,NUMERIC_FLOAT,NUMERIC_DOUBLE
	 * @return
	 */
	public static boolean isNumeric(String str, int intType) {
		try {
			switch (intType) {
			case NUMERIC_SHORT:
				Short.parseShort(str);
				break;
			case NUMERIC_INT:
				Integer.parseInt(str);
				break;
			case NUMERIC_LONG:
				Long.parseLong(str);
				break;
			case NUMERIC_FLOAT:
				Float.parseFloat(str);
				break;
			case NUMERIC_DOUBLE:
				Double.parseDouble(str);
				break;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/****
	 * 判断是否是email地址
	 * @param email
	 * @return	
	 */
	public static boolean isEmail(String email) {
		if ( isEmpty( email ) )
			return false;
		if (email.indexOf("@") <= 0)
			return false;
		int len = email.length();
		if (len <= 3)
			return false;
		String name = email.substring(0, email.indexOf("@"));
		if (name.length() <= 1)
			return false;
		String hostname = email.substring(email.indexOf("@") + 1);
		if (hostname.indexOf("@") >= 0)
			return false;
		if (hostname.indexOf(".") <= 0)
			return false;
		if (hostname.indexOf(".") > (hostname.length() - 3))
			return false;
		String domainname = hostname.substring(hostname.lastIndexOf(".") + 1);
		if (domainname.length() < 2)
			return false;
		if (isNumeric(domainname, NUMERIC_INT))
			return false;
		return true;
	}
	
	/**
	 * 将字符串转化为小写字串
	 * @param formatstr
	 * @param mark		0:小写;1:大写
	 * @return			转换后的字符
	 */
	public static String toFormat(String formatstr,int mark) {
		if ( isNotEmpty( formatstr ) ) {
			switch(mark){
			case 0:
				return formatstr.toLowerCase();
			case 1 :
				return formatstr.toUpperCase() ;
			}
		}
		return "" ;
	}
	
	/***
	 * 去除字符数组中重复的数据
	 * @param data
	 * @return
	 */
	public static String [] deleteRepetition( String [] data ) {  
    	if( data != null && data.length > 0 ){
	        List<String> list = new ArrayList<String>();  
	        for (int i=0; i<data.length; i++) {  
	            if(!list.contains(data[i])) {  
	                list.add(data[i]);  
	            }  
	        }  
	        return list.toArray(new String[1]); //返回一个包含所有对象的指定类型的数组   
    	}else{
    		return null ;
    	}
    }  

	/**
	 * 将一个字符串格式化为给定的长度，过长的话按照给定的长度从字符串左边截取，反之以给定的 字符在字符串右边补足空余位 <br>
	 * 比如： <br>
	 * suffixStr("abc",'0',5) 将返回 aaa00 <br>
	 * suffixStr("abc",'0',2) 将返回 ab
	 * @param source 原始字符串
	 * @param profix 补足空余位时使用的字符串
	 * @param length 格式化后字符串长度
	 * @return 返回格式化后的字符串,异常返回null
	 */
	public static String suffixStr(String source, char suffix, int length) {
		String strRet = source;
		if (source == null) {
			return strRet;
		}
		if (source.length() >= length) {
			strRet = source.substring(0, length);
		}
		if (source.length() < length) {
			for (int i = 0; i < length - source.length(); i++) {
				strRet += suffix;
			}
		}
		return strRet;
	}

	/**
	 * 根据分割符sp，将str分割成多个字符串，并将它们放入一个ArrayList并返回，其规则是最后的 字符串最后add到ArrayList中
	 * @param str  被分割的字符串
	 * @param sp   分割符字符串
	 * @return 封装好的ArrayList
	 */
	public static List<String> convertStrToArrayList(String str, String sp) {
		List<String> al = new ArrayList<String>();
		if (str == null) {
			return al;
		}
		String strArr[] = str.split(sp);
		for (int i = 0; i < strArr.length; i++) {
			al.add(strArr[i]);
		}
		return al;
	}
	
	/**
	 * 默认保留小数点后两位，将一个浮点数转换为货币的显示格式：##,###.##
	 * @param value  浮点数
	 * @return 货币格式显示的数字
	 */
	public static String toCurrency(double value) {
		return toCurrency(value, 2);
	}

	/**
	 * 根据指定的小数位数，将一个浮点数转换为货币的显示格式
	 * @param value 浮点数
	 * @param decimalDigits 小数点后保留小数位数
	 * @return 货币格式显示的数字    例： toCurrency(123456.789,5) 将返回 "123,456.78900"
	 */
	public static String toCurrency(double value, int decimalDigits) {
		String format = "#,##0." + repeat("0", decimalDigits);
		NumberFormat nf = new DecimalFormat(format);
		return nf.format(value);
	}
	
	/**
	 * 重复一个字符串 n 次，比如 abcabcabc
	 * @param str 需要重复的字符串
	 * @param repeat  重复的次数
	 * @return 重复后生成的字符串
	 */
	public static String repeat(String str, int repeat) {
		StringBuffer buffer = new StringBuffer(repeat * str.length());
		for (int i = 0; i < repeat; i++) {
			buffer.append(str);
		}
		return buffer.toString();
	}
	
	/***
	 * 判断字符是否是文本字符
	 * @param str
	 * @return
	 */
	public static boolean hasText(CharSequence str) {
		if (hasLength(str)) {
			int strLen = str.length();
			for (int i = 0; i < strLen; i++) {
				if (!Character.isWhitespace(str.charAt(i))) {
					return true;
				}
			}
		}		
		return false;
	}
	
	/***
	 * 判断字符不为空且长度大于0
	 * @param str
	 * @return
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
	
	
	/***
	 * 将字符串转换成int类型,如果字符串为空或者转换异常 均返回：0
	 * @param str
	 * @return			转换后的值
	 */
	@Deprecated
	public static int getIntByStr(String str) {
		if ( isEmpty(str) ) {
			return 0;
		} else {
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				return 0 ;
			}
		}
	}
	
	/***
	 * 将字符串转换成long类型,如果字符串为空或者转换异常 均返回：0
	 * @param str
	 * @return			转换后的值
	 */
	@Deprecated
	public static long getLongByStr(String str) {
		if ( isEmpty(str) ) {
			return 0;
		} else {
			try {
				return Long.parseLong(str);
			} catch (NumberFormatException e) {
				return 0 ;
			}
		}
	}
	
	/***
	 * 将字符串转换成float类型,如果字符串为空或者转换异常 均返回：0
	 * @param str
	 * @return			转换后的值
	 */
	@Deprecated
	public static float getFloat(String str ) {
		if ( isEmpty(str) ) {
			return 0f;
		}else{
			try {
				return Float.parseFloat(str);
			} catch (Exception e) {
				return 0f;
			}
		}
	}

	/***
	 * 将字符串转换成double类型,如果字符串为空或者转换异常 均返回：0
	 * @param str
	 * @return			转换后的值
	 */
	@Deprecated
	public static double getDoubleByStr(String str) {
		if ( isEmpty(str) ) {
			return 0;
		} else {
			try {
				return Double.parseDouble(str);
			} catch (NumberFormatException e) {
				return 0 ;
			}
		}
	}
	
	/**
	 * 判断字符串是否是JSON 格式 的字符串
	 * @param jsonStr
	 * @return  true - 是JSON 格式的字符串， 反之为 false
	 */
	public static boolean isJsonStr(String jsonStr){
		if(isEmpty(jsonStr)){
			return false;
		}		
		if(jsonStr.startsWith("{") && jsonStr.endsWith("}")){
			return true;
		}		
		return false;
	}
	
	
	// 将汉字转换为全拼  
    public static String getPingYin(String src) {    
        char[] t1 = null;  
        t1 = src.toCharArray();  
        String[] t2 = new String[t1.length];  
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();            
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
        String t4 = "";  
        int t0 = t1.length;  
        try {  
            for (int i = 0; i < t0; i++) {  
                // 判断是否为汉字字符  
                if (java.lang.Character.toString(t1[i]).matches(  
                        "[\\u4E00-\\u9FA5]+")) {  
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);  
                    t4 += t2[0];  
                } else  
                    t4 += java.lang.Character.toString(t1[i]);  
            }  
            // System.out.println(t4);  
            return t4;  
        } catch (BadHanyuPinyinOutputFormatCombination e1) {  
            e1.printStackTrace();  
        }  
        return t4;  
    }  
  
    // 返回中文的首字母  
    public static String getPinYinHeadChar(String str) {    
        String convert = "";  
        for (int j = 0; j < str.length(); j++) {  
            char word = str.charAt(j);  
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);  
            if (pinyinArray != null) {  
                convert += pinyinArray[0].charAt(0);  
            } else {  
                convert += word;  
            }  
        }  
        return convert;  
    }  
  
    // 将字符串转移为ASCII码  
    public static String getCnASCII(String cnStr) {  
        StringBuffer strBuf = new StringBuffer();  
        byte[] bGBK = cnStr.getBytes();  
        for (int i = 0; i < bGBK.length; i++) {  
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));  
        }  
        return strBuf.toString();  
    }  
  
    public static void main(String[] args) {  
        System.out.println(getPingYin("长沙市"));  
        System.out.println(getPingYin("河南省"));  
        System.out.println(getPingYin("何刚强"));  
        System.out.println(getPinYinHeadChar("长沙市"));  
        System.out.println(getPinYinHeadChar("河南省"));  
        System.out.println(getPinYinHeadChar("何刚强"));   
    }  
	
}
