package cn.tuyucheng.taketoday.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class AppConfigUnitTest {

	@Autowired
	@Qualifier("storeThroughConstructorInjection")
	private Store storeByConstructorInjection;

	@Autowired
	@Qualifier("storeThroughSetterInjection")
	private Store storeBySetterInjection;

	@Test
	void givenValidXmlConfig_WhenInjectStoreByConstructorInjection_ThenBeanIsNotNull() {
		assertNotNull(storeByConstructorInjection);
		assertNotNull(storeByConstructorInjection.getItem());
	}

	@Test
	void givenValidXmlConfig_WhenInjectStoreBySetterInjection_ThenBeanIsNotNull() {
		assertNotNull(storeBySetterInjection);
		assertNotNull(storeByConstructorInjection.getItem());
	}
}