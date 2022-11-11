package cn.tuyucheng.taketoday.ehcache.config;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class CacheHelper {

	private final CacheManager cacheManager;

	private final Cache<Integer, Integer> squareNumberCache;

	public CacheHelper() {
		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
		cacheManager.init();
		squareNumberCache = cacheManager.createCache("squareNumberCache",
				CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Integer.class, ResourcePoolsBuilder.heap(10)));
	}

	public Cache<Integer, Integer> getSquareNumberCache() {
		return squareNumberCache;
	}

	public Cache<Integer, Integer> getSquareNumberCacheFromCacheManager() {
		return cacheManager.getCache("squareNumberCache", Integer.class, Integer.class);
	}
}