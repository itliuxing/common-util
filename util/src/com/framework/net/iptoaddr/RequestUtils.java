package com.framework.net.iptoaddr;

import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.framework.http.getpost.HttpReqUtil;

/**
 * @Class 	RequestUtils.java
 * @Author 	作者姓名:刘兴
 * @Version	1.0
 * @Date	创建时间：2018-7-13 上午9:08:13
 * @Copyright Copyright by 智多星
 * @Direction 类说明
 */
public class RequestUtils {
	
	//淘宝IP获取对应的服务所在的信息
	private final static String TaoBaoURL = "http://ip.taobao.com/service/getIpInfo.php?ip=" ;
	
	public static Logger requestLog = Logger.getLogger( RequestUtils.class ) ; 
	
	/***
	 * 获取用户点击的请求信息
	 * @param req
	 */
	public static boolean requestInfoIsAli( HttpServletRequest req ){
		boolean isAli = false ;
		
		StringBuffer requestInfo = new StringBuffer() ;
		String clientIP = getIpAddr(req) ;
		requestInfo.append("\n 请求IP : " ).append( clientIP ) ;
		 //获取请求方式
		requestInfo.append( "\n 请求Method : " ).append(  req.getMethod());
        //获取项目名称
        //requestInfo.append( "\n" ).append(  req.getContextPath());
        //获取完整请求路径
        requestInfo.append( "\n" ).append(  req.getRequestURL());
        //获取除了域名外的请求数据
        requestInfo.append( "\n" ).append(  req.getRequestURI());
        //获取请求参数
        requestInfo.append( "\n" ).append(  req.getQueryString());
        requestInfo.append( "\n" ).append(  "----------------------------------------------------------");
        //获取请求头
        String header = req.getHeader("user-Agent");

		if( header.indexOf ("AliApp(") > -1 ){
			isAli = true ; 
		}else{
			try {
				String ipinfo = getIPToAddr( clientIP ) ;
				if( ipinfo.indexOf("阿里巴巴") > -1 || ipinfo.indexOf("阿里云") > -1 ){
					isAli = true ; 
				}
			} catch (Exception e) {
				e.printStackTrace();
				requestLog.error("淘宝解析IP地址接口出现异常,前往检查，类地址：servicesmng.sendMessage.utils.RequestUtils ") ;
			}
		}
        requestInfo.append( "\n" ).append(  header);
        header = header.toLowerCase();
        //根据请求头数据判断浏览器类型
        if(header.contains("chrome")){
            requestInfo.append( "\n" ).append(  "您的访问浏览器为谷歌浏览器");
        }else if(header.contains("msie")){
            requestInfo.append( "\n" ).append(  "您的访问浏览器为IE浏览器");
        }else if(header.contains("firefox")){
            requestInfo.append( "\n" ).append(  "您的访问浏览器为火狐浏览器");
        }else{
            requestInfo.append( "\n" ).append(  "您的访问浏览器为其它浏览器 : " + header );
        }
       /* requestInfo.append( "\n" ).append(  "----------------------------------------------------------");
        //获取所有的消息头名称
        Enumeration<String> headerNames = req.getHeaderNames();
        //获取获取的消息头名称，获取对应的值，并输出
        while(headerNames.hasMoreElements()){
            String nextElement = headerNames.nextElement();
            requestInfo.append( "\n" ).append(  nextElement+":"+req.getHeader(nextElement));
        }
        requestInfo.append( "\n" ).append(  "----------------------------------------------------------");
        //根据名称获取此重名的所有数据
        Enumeration<String> headers = req.getHeaders("accept");
        while (headers.hasMoreElements()) {
            String string = (String) headers.nextElement();
            requestInfo.append( "\n" ).append(  string);
        }*/
        //System.out.println( requestInfo.toString() );
        //requestLog.error(requestInfo) ;
        return isAli ;
	}
	
	public static String getIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("X-Forwarded-For");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }  
	
	/***
	 * 根据IP地址获取服务商的信息
	 * @param IP
	 * @throws Exception
	 */
	public static String getIPToAddr( String IP ) throws Exception{
		HttpResponse httpResponse = HttpReqUtil.getObjectReq( TaoBaoURL + IP , null ) ; //getObjectReq
		String resultStr = "" ;
		IpToAddr result = null;
		if( httpResponse != null ){
			// 请求得到响应后，分析只有200的时候才去分析得到的数据，否认都任务失败
			if (httpResponse.getStatusLine().getStatusCode() == 200) { 
				try {
					result = (IpToAddr)httpResponse.getEntity();
				} catch (Exception e) {
					resultStr = EntityUtils.toString(httpResponse.getEntity(),Charset.forName("UTF-8")); 
					// 将返回的数据解析成一个字符串					
					requestLog.info( IP + "==IP==解析出来的STR.\n" + resultStr ) ;
					
					JSONObject  jsonObject = JSONObject.parseObject( resultStr );
				    //json对象转Map
				    Map<String,Object> map = (Map<String,Object>)jsonObject;
				    map = (Map<String,Object>) map.get("data") ;
				    return map.get("isp").toString() ;
				
					//result = (IpToAddr) JSONObject.parseObject( resultStr , IpToAddr.class ) ;
				}
			}
		}
		return result.getData().getIsp() ;
	}


}
