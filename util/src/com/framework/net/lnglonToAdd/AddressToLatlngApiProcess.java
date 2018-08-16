package com.framework.net.lnglonToAdd;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Class 	AddressToLatlng.java
 * @Author 	作者姓名:刘兴 
 * @Version	1.0
 * @Date	创建时间：2017-5-6 下午12:31:44
 * @Copyright Copyright by 智多星
 * @Direction 类说明				地址转换成经纬度,经纬度要转换再写一个哦
 */

public class AddressToLatlngApiProcess {
	
	private final static String gaodekey = "a0fa1343b8ff7b0d4092827ad6d22e7b" ;
	private final static String urlPath = "http://restapi.amap.com/v3/geocode/geo?address=" ;
	
	/** 
     * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果 
     * @param content 
     * @return 
     */  
    public static String getGaodeResult(String companyAddress){  
        /** 此处为搞得地图根据地址解析经纬度api接口，参数key需要自己去注册申请，本人key:ee0f8f7f30c1d691d7da396524c7a310 */  
    	//	http://restapi.amap.com/v3/geocode/geo?address=北京市朝阳区阜通东大街6号&output=XML&key=<用户的key>
    	StringBuffer apiUrl = new StringBuffer( urlPath ) ; //.append( companyAddress ).append("&output=json&key=").append( gaodekey );  
        try {  
            apiUrl.append( URLEncoder.encode(companyAddress,"utf-8") ).append("&output=json&key=").append( gaodekey ); 
        } catch (UnsupportedEncodingException e1) {  
        } //将参数转为url编码  
          
        /** 发送httpget请求 */  
        HttpGet request = new HttpGet( apiUrl.toString() );  
        String result = "";  
        try {  
            HttpResponse response = HttpClients.createDefault().execute(request);  
            if(response.getStatusLine().getStatusCode()==200){  
                result = EntityUtils.toString(response.getEntity());  
            }  
        } catch (ClientProtocolException e) {  
            //e.printStackTrace();  
        } catch (IOException e) {  
            //e.printStackTrace();  
        }  
        return result;  
    }  
    
    /***
     * 对数据进行初始化封装		多条数据查询，最多十条
     * @param company
     * @return
     */
    public static ResultJson encapsulationQueryData( String ... companys ){
    	StringBuffer companyAdrray = new StringBuffer() ;
    	int i = 1 ;
    	for(String company : companys){
    		companyAdrray.append( company ) ;
    		if( companys.length < i){
    			companyAdrray.append( "|" ) ;
    		}
    		i += 1 ;
    	}
    	String result = getGaodeResult( companyAdrray.toString() ) ;
    	ResultJson resultJson = JSON.parseObject( result , ResultJson.class ) ;
    	return resultJson ;
    }
	
    /***
     * 对数据进行初始化封装		单跳数据查询
     * @param company
     * @return
     */
    public static ResultJson encapsulationQueryDataOne( String company ){
    	String result = getGaodeResult(company) ;
    	ResultJson resultJson = JSON.parseObject( result , ResultJson.class ) ;
    	return resultJson ;
    }
    
    public static void main(String[] args) {
    	ResultJson resultJson = encapsulationQueryData( "长沙市湖南商会大厦" , "www" ) ;
		System.out.println( resultJson.getCount() );
		System.out.println( resultJson.getGeocodes().size() > 0 ? resultJson.getGeocodes().get(0).getLocation() : "" );
	}

}
