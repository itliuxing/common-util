package com.framework.string;

import java.util.regex.Matcher;  
import java.util.regex.Pattern; 


/**
 * @Class 	HtmlFiltorUtil.java
 * @Author 	作者姓名:刘兴 
 * @Version	1.0
 * @Date	创建时间：2017-9-6 上午10:49:34
 * @Copyright Copyright by 智多星
 * @Direction 类说明
 */

public class HtmlFiltorUtil {

	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
    
    /**
     * @param htmlStr
     * @return
     *  删除Html标签
     */
    public static String delHTMLTag(String htmlStr) {
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        return htmlStr.trim(); // 返回文本字符串
    }
    
    /**
     * @param htmlStr
     * @return
     *  获取删除Html标签文字指定长度的信息
     */
    public static String delHTMLTagHight(String htmlStr , int length ) {
       String info = delHTMLTag(htmlStr) ;
       if( length < 1){
    	   length = 150 ;
       }
       if( StringUtils.isNotEmpty(info) ){
    	   if( info.length() < 150 ){
    		   return info.toString() ;
    	   }else{
    		   return info.substring(0, 149) + "..." ;
    	   }
       }else{
    	   return "" ;
       }
    }
    
    public static String getTextFromHtml(String htmlStr){
    	htmlStr = delHTMLTag(htmlStr);
    	htmlStr = htmlStr.replaceAll("&nbsp;", "");
    	htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);
    	return htmlStr;
    }
    
    public static void main(String[] args) {
    	String str = "<div style='text-align:center;'> 整治“四风”   清弊除垢<br/><span style='font-size:14px;'> </span><span style='font-size:18px;'>公司召开党的群众路线教育实践活动动员大会。</span><br/></div>";
    	System.out.println( (str));
    	System.out.println(getTextFromHtml(str));
	}
	
}
