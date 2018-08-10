package com.framework.db.memcached;

import java.util.ArrayList;
import java.util.List;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

public class MemcachedTest {
	
	@SuppressWarnings("unchecked")  
    public static void main(String[] args) {  
        ICacheManager<IMemcachedCache> manager;  
        manager = CacheUtil.getCacheManager(IMemcachedCache.class,  
                MemcachedCacheManager.class.getName());  
        manager.setConfigFile( "config/memcached.xml");  
        manager.start();  
        try {  
            IMemcachedCache cache = manager.getCache("mclient_0");  
            cache.put("key", "value");  
            System.out.println(cache.get("key"));  
        } finally {  
            manager.stop();  
        }  
    }  

}
