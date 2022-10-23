package cn.tuyucheng.taketoday.caching.test;

import cn.tuyucheng.taketoday.caching.config.CachingConfig;
import cn.tuyucheng.taketoday.caching.example.Customer;
import cn.tuyucheng.taketoday.caching.example.CustomerDataService;
import cn.tuyucheng.taketoday.caching.example.CustomerServiceWithParent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CachingConfig.class}, loader = AnnotationConfigContextLoader.class)
class SpringCachingIntegrationTest {

    @Autowired
    private CustomerDataService service;

    @Autowired
    private CustomerServiceWithParent serviceWithParent;

    @Test
    void whenGettingAddress_thenCorrect() {
        final Customer customer = new Customer("Tom", "67-2, Downing Street, NY");
        service.getAddress(customer);
        service.getAddress(customer);

        service.getAddress1(customer);
        service.getAddress1(customer);

        service.getAddress2(customer);
        service.getAddress2(customer);

        service.getAddress3(customer);
        service.getAddress3(customer);

        service.getAddress4(customer);
        service.getAddress4(customer);

        service.getAddress5(customer);
        service.getAddress5(customer);
    }

    @Test
    void givenUsingServiceWithParent_whenGettingAddress_thenCorrect() {
        final Customer customer = new Customer("Tom", "67-2, Downing Street, NY");

        serviceWithParent.getAddress(customer);
        serviceWithParent.getAddress(customer);

        serviceWithParent.getAddress1(customer);
        serviceWithParent.getAddress1(customer);

        serviceWithParent.getAddress2(customer);
        serviceWithParent.getAddress2(customer);

        serviceWithParent.getAddress3(customer);
        serviceWithParent.getAddress3(customer);

        serviceWithParent.getAddress5(customer);
        serviceWithParent.getAddress5(customer);
    }
}