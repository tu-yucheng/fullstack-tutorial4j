package cn.tuyucheng.taketoday.injectionproblem;

import cn.tuyucheng.taketoday.injectionproblem.prototype.PrototypeBean;
import cn.tuyucheng.taketoday.injectionproblem.singletone.SingletonLookupBean;
import cn.tuyucheng.taketoday.injectionproblem.singletone.SingletonObjectFactoryBean;
import cn.tuyucheng.taketoday.injectionproblem.singletone.SingletonProviderBean;
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
	void givenPrototypeInjection_whenObjectFactory_thenNewInstanceReturn() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		SingletonObjectFactoryBean firstContext = context.getBean(SingletonObjectFactoryBean.class);
		SingletonObjectFactoryBean secondContext = context.getBean(SingletonObjectFactoryBean.class);

		PrototypeBean firstInstance = firstContext.getPrototypeInstance();
		PrototypeBean secondInstance = secondContext.getPrototypeInstance();

		assertTrue("New instance expected", firstInstance != secondInstance);
	}

	@Test
	void givenPrototypeInjection_whenLookup_thenNewInstanceReturn() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		SingletonLookupBean firstContext = context.getBean(SingletonLookupBean.class);
		SingletonLookupBean secondContext = context.getBean(SingletonLookupBean.class);

		PrototypeBean firstInstance = firstContext.getPrototypeBean();
		PrototypeBean secondInstance = secondContext.getPrototypeBean();

		assertTrue("New instance expected", firstInstance != secondInstance);
	}

	@Test
	void givenPrototypeInjection_whenProvider_thenNewInstanceReturn() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		SingletonProviderBean firstContext = context.getBean(SingletonProviderBean.class);
		SingletonProviderBean secondContext = context.getBean(SingletonProviderBean.class);

		PrototypeBean firstInstance = firstContext.getPrototypeInstance();
		PrototypeBean secondInstance = secondContext.getPrototypeInstance();

		assertTrue("New instance expected", firstInstance != secondInstance);
	}
}