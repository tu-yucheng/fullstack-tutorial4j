package cn.tuyucheng.taketoday.caching.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
public class SimpleCacheCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {
    static final String USER_CACHE = "users";
    static final String TRANSACTIONS_CACHE = "transactions";
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCacheCustomizer.class);

    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
        LOGGER.info("Customizing Cache Manager");
        cacheManager.setCacheNames(asList(USER_CACHE, TRANSACTIONS_CACHE));
    }
}