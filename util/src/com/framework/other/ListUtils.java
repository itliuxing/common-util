package com.framework.other;

import java.util.ArrayList;
import java.util.List;

/**
* @Class 	ListUtils.java
* @Author 	作者姓名:刘兴 
* @Version	1.0
* @Date		创建时间：2018年9月3日 下午5:43:58
* @Copyright Copyright by 刘兴
* @Direction 类说明	工具源码来自：https://blog.csdn.net/Alice_qixin/article/details/78522946
*/
public class ListUtils {

	
	/** 
	 * 将一个list均分成n个list,主要通过偏移量来实现的 
	 * @param source 
	 * @return 
	 */  
	public static <T> List<List<T>> averageAssign(List<T> source,int n){  
	    List<List<T>> result=new ArrayList<List<T>>();  
	    int remaider=source.size()%n;  //(先计算出余数)  
	    int number=source.size()/n;  //然后是商  
	    int offset=0;//偏移量  
	    for(int i=0;i<n;i++){  
	        List<T> value=null;  
	        if(remaider>0){  
	            value=source.subList(i*number+offset, (i+1)*number+offset+1);  
	            remaider--;  
	            offset++;  
	        }else{  
	            value=source.subList(i*number+offset, (i+1)*number+offset);  
	        }  
	        result.add(value);  
	    }  
	    return result;  
	} 

	
}
