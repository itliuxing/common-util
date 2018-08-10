package com.framework.db.memcached;

import java.util.ArrayList;
import java.util.List;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;
import com.framework.vo.Json;

/****
 * *
 * 类名称：		MemcachedSimple.java 
 * 类描述：   		
 * 创建人：		
 * 创建时间：		2016-1-15上午11:36:44 
 * 修改人：		liuxing
 * 修改时间：		2016-1-15上午11:36:44 
 * 修改备注：   		
 * @version
 */
public class MemcachedSimple {
	
	@SuppressWarnings("unchecked")  
    public static void main(String[] args) {  
        ICacheManager<IMemcachedCache> manager;  
        manager = CacheUtil.getCacheManager(IMemcachedCache.class,  
                MemcachedCacheManager.class.getName());  
        manager.setConfigFile("config/memcached.xml");  
        manager.start();  
        try {  
            IMemcachedCache cache = manager.getCache("mclient_0");  
  
            Json bean=new Json();  
            bean.setMsg("name1");  
            bean.setObj(25);  
            cache.put("bean", bean);  
            Json beanClient=(Json)cache.get("bean");  
            System.out.println(beanClient.getMsg());  
              
            List<Json> list=new ArrayList<Json>();  
            list.add(bean);  
            cache.put("beanList", list);  
            List<Json> listClient=(List<Json>)cache.get("beanList");  
            if(listClient.size()>0){  
            	Json bean4List=listClient.get(0);  
                System.out.println(bean4List.getMsg());  
            }  
        } finally {  
            manager.stop();  
        }  
    }  

}
