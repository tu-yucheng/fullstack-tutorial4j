package cn.tuyucheng.boot.jackson.app;

import cn.tuyucheng.boot.jackson.config.CoffeeObjectMapperConfig;
import org.springframework.context.annotation.Import;

@Import(CoffeeObjectMapperConfig.class)
class CoffeeObjectMapperIntegrationTest extends AbstractCoffeeIntegrationTest {
    
}