package cn.tuyucheng.taketoday.di.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BeanInjectionIntegrationTest {

    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new ClassPathXmlApplicationContext("cn.tuyucheng.taketoday.di.spring.xml");
    }

    @Test
    void singletonBean_getBean_returnsSingleInstance() {
        final IndexApp indexApp1 = applicationContext.getBean("indexApp", IndexApp.class);
        final IndexApp indexApp2 = applicationContext.getBean("indexApp", IndexApp.class);
        assertEquals(indexApp1, indexApp2);
    }

    @Test
    void getBean_returnsInstance() {
        final IndexApp indexApp = applicationContext.getBean("indexApp", IndexApp.class);
        assertNotNull(indexApp);
    }
}