package com.eypg.util;

import java.util.Date;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;


public class MemCachedClientHelp {

    public static IMemcachedCache iMemcachedCache = null;

    public static IMemcachedCache getIMemcachedCache() {

        if (iMemcachedCache != null)
            return iMemcachedCache;

        ICacheManager<IMemcachedCache> manager;
        manager = CacheUtil.getCacheManager(IMemcachedCache.class, MemcachedCacheManager.class.getName());
        manager.setConfigFile("memcached.xml");
        manager.start();
        iMemcachedCache = manager.getCache("mclient");

        return iMemcachedCache;
    }

    public static void main(String[] args) throws InterruptedException {

//		getIMemcachedCache().put("测试微博预警TrackClientWarning", 55 ,new Date(12*60*60*1000));
        String key = MD5Util.encode("测试微博预警TrackClientWarning");
        System.err.println(key);
        getIMemcachedCache().put(key, 55, new Date(3000));
        Thread.sleep(2000);
        System.out.println(getIMemcachedCache().get(key));
        Thread.sleep(1000);
        System.out.println(getIMemcachedCache().get(key));

    }

}
 