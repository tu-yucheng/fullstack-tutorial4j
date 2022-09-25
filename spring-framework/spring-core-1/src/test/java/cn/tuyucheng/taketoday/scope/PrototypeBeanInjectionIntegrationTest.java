package cn.tuyucheng.taketoday.scope;

import cn.tuyucheng.taketoday.scope.prototype.PrototypeBean;
import cn.tuyucheng.taketoday.scope.singletone.SingletonLookupBean;
import cn.tuyucheng.taketoday.scope.singletone.SingletonObjectFactoryBean;
import cn.tuyucheng.taketoday.scope.singletone.SingletonProviderBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = AppConfig.class)
class PrototypeBeanInjectionIntegrationTest {

    @Test
    @DisplayName("givenPrototypeInjection_WhenObjectFactory_ThenNewInstanceReturn")
    void givenPrototypeInjection_WhenObjectFactory_ThenNewInstanceReturn() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SingletonObjectFactoryBean firstContext = context.getBean(SingletonObjectFactoryBean.class);
        SingletonObjectFactoryBean secondContext = context.getBean(SingletonObjectFactoryBean.class);

        PrototypeBean firstInstance = firstContext.getPrototypeInstance();
        PrototypeBean secondInstance = secondContext.getPrototypeInstance();

        assertTrue("New instance expected", firstInstance != secondInstance);
    }

    @Test
    @DisplayName("givenPrototypeInjection_WhenLookup_ThenNewInstanceReturn")
    void givenPrototypeInjection_WhenLookup_ThenNewInstanceReturn() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SingletonLookupBean firstContext = context.getBean(SingletonLookupBean.class);
        SingletonLookupBean secondContext = context.getBean(SingletonLookupBean.class);

        PrototypeBean firstInstance = firstContext.getPrototypeBean();
        PrototypeBean secondInstance = secondContext.getPrototypeBean();

        assertTrue("New instance expected", firstInstance != secondInstance);
    }

    @Test
    @DisplayName("givenPrototypeInjection_WhenProvider_ThenNewInstanceReturn")
    void givenPrototypeInjection_WhenProvider_ThenNewInstanceReturn() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SingletonProviderBean firstContext = context.getBean(SingletonProviderBean.class);
        SingletonProviderBean secondContext = context.getBean(SingletonProviderBean.class);

        PrototypeBean firstInstance = firstContext.getPrototypeInstance();
        PrototypeBean secondInstance = secondContext.getPrototypeInstance();

        assertTrue("New instance expected", firstInstance != secondInstance);
    }
}