package com.framework.sensitiveWord;

import java.util.Set;

/**
 * @Class 	SensitiUtilTest.java
 * @Author 	作者姓名:刘兴 
 * @Version	1.0
 * @Date	创建时间：2017-8-9 上午8:33:22
 * @Copyright Copyright by 智多星
 * @Direction 类说明
 */
public class SensitiUtilTest {
	
	public static void main(String[] args) {
        //SensitivewordFilter filter = new SensitivewordFilter();
        System.out.println("敏感词的数量：" + SensitivewordFilter.geneartorSensiFilter().sensitiveWordMap.size());
        String string = "台湾猪太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                        + "然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                        + "傻逼难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
        System.out.println("待检测语句字数：" + string.length());
        long beginTime = System.currentTimeMillis();
        Set<String> set = SensitivewordFilter.geneartorSensiFilter().getSensitiveWord(string, 1);
        long endTime = System.currentTimeMillis();
        System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
        System.out.println("总共消耗时间为：" + (endTime - beginTime));
        verify(string) ;			//使用关键字过滤器
    }

	public static void verify( String info ){
		System.out.println( SensitivewordUtil.sensitivewordFiltorToString(info) );
		System.out.println( SensitivewordUtil.sensitivewordFiltorToBoolean(info) );
		System.out.println( SensitivewordUtil.sensitivewordFiltorToSet(info));
	}
}
