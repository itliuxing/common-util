package com.framework.sensitiveWord;

import java.util.HashSet;
import java.util.Set;

import com.framework.string.StringUtils;


/**
 * @Class 	SensitiUtilTest.java
 * @Author 	作者姓名:刘兴 
 * @Version	1.0
 * @Date	创建时间：2017-8-9 上午9:33:22
 * @Copyright Copyright by 智多星
 * @Direction 类说明
 */
public class SensitivewordUtil {
	
	/***
	 * 关键字识别--返回set 关键字信息
	 * @param info
	 * @return
	 */
	public static Set<String> sensitivewordFiltorToSet( String info ){
		if( StringUtils.isNotEmpty(info) ){
			return SensitivewordFilter.geneartorSensiFilter().getSensitiveWord( info, 1 );
		}
		return new HashSet<String>() ;
	}
	
	/***
	 * 关键字识别--返回String 关键字信息
	 * @param info
	 * @return
	 */
	public static String sensitivewordFiltorToString( String info ){
		StringBuffer filtor = new StringBuffer() ;
		if( StringUtils.isNotEmpty(info) ){
			Set<String> set = SensitivewordFilter.geneartorSensiFilter().getSensitiveWord( info, 1 );
			for( String code : set ){
				filtor.append( code ).append(" ") ;
			}
		}
		return filtor.toString() ;
	}
	
	/***
	 * 关键字识别--返回boolean 关键字信息
	 * @param info	含有敏感字符	返回:true 不含有就返回:false
	 * @return
	 */
	public static boolean sensitivewordFiltorToBoolean( String info ){
		if( StringUtils.isNotEmpty(info) ){
			Set<String> set = SensitivewordFilter.geneartorSensiFilter().getSensitiveWord( info, 1 );
			if( set != null && set.size() > 0 ){
				return true ;
			}
		}
		return false ;
	}
	
	
}
