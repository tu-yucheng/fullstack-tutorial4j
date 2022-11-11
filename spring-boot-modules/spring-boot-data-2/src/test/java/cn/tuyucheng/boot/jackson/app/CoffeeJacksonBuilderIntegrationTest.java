package cn.tuyucheng.boot.jackson.app;

import cn.tuyucheng.boot.jackson.config.CoffeeJacksonBuilderConfig;
import org.springframework.context.annotation.Import;

@Import(CoffeeJacksonBuilderConfig.class)
class CoffeeJacksonBuilderIntegrationTest extends AbstractCoffeeIntegrationTest {

}