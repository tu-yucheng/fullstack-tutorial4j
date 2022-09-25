package cn.tuyucheng.taketoday.bean.injection;

import cn.tuyucheng.taketoday.bean.config.ConstructorBasedShipConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConstructorBasedBeanInjectionWithJavaConfigIntegrationTest {
    private static final String HELM_NAME = "HelmBrand";

    @Test
    public void givenJavaConfigFile_whenUsingConstructorBasedBeanInjection_thenCorrectHelmName() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ConstructorBasedShipConfig.class);
        ctx.refresh();

        Ship ship = ctx.getBean(Ship.class);

        Assertions.assertEquals(HELM_NAME, ship.getHelm().getBrandOfHelm());
    }
}
