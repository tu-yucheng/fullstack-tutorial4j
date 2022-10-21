package cn.tuyucheng.taketoday.store;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:/ioc-context.xml")
class XmlAppConfigUnitTest {

    @Autowired
    @Qualifier("xml-store-by-constructor")
    private Store storeByConstructorInjection;

    @Autowired
    @Qualifier("xml-store-by-setter")
    private Store storeBySetterInjection;

    @Autowired
    @Qualifier("xml-store-by-autowire-name")
    private Store storeByAutowireInjectionByName;

    @Autowired
    @Qualifier("xml-store-by-setter-lazy")
    private Store storeBySetterInjectionLazy;

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

    @Test
    void givenValidXmlConfig_WhenInjectStoreByAutowireInjectionByName_ThenBeanIsNotNull() {
        assertNotNull(storeByAutowireInjectionByName);
        assertNotNull(storeByAutowireInjectionByName.getItem());
    }

    @Test
    void givenValidXmlConfig_WhenInjectStoreBySetterInjectionLazy_ThenBeanIsNotNull() {
        assertNotNull(storeBySetterInjectionLazy);
        assertNotNull(storeByConstructorInjection.getItem());
    }
}