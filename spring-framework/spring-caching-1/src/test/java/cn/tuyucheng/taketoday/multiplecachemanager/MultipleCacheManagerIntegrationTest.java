package cn.tuyucheng.taketoday.multiplecachemanager;

import cn.tuyucheng.taketoday.multiplecachemanager.bo.OrderDetailBO;
import cn.tuyucheng.taketoday.multiplecachemanager.entity.Order;
import cn.tuyucheng.taketoday.multiplecachemanager.repository.OrderDetailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootApplication
@SpringBootTest
class MultipleCacheManagerIntegrationTest {

    @MockBean
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailBO orderDetailBO;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CacheManager alternateCacheManager;

    @Test
    void givenCacheResolverIsConfigured_whenCallGetOrderDetail_thenDataShouldBeInCaffieneCacheManager() {
        Integer key = 30001;
        cacheManager.getCache("orders").evict(key);
        Order order = new Order();
        order.setCustomerId(1001);
        order.setItemId(10001);
        order.setOrderId(30001);
        order.setQuantity(2);
        when(orderDetailRepository.getOrderDetail(key)).thenReturn(order);
        orderDetailBO.getOrderDetail(key);
        CaffeineCache cache = (CaffeineCache) cacheManager.getCache("orders");
        assertNotNull(cache.get(key).get(), "caffieneCache should have had the data");
    }

    @Test
    void givenCacheResolverIsConfigured_whenCallGetOrderPrice_thenDataShouldBeInAlternateCacheManager() {
        Integer key = 30001;
        alternateCacheManager.getCache("orderprice").evict(key);
        when(orderDetailRepository.getOrderPrice(key)).thenReturn(500.0);
        orderDetailBO.getOrderPrice(key);
        Cache cache = alternateCacheManager.getCache("orderprice");
        assertNotNull(cache.get(key).get(), "alternateCache should have had the data");
    }
}