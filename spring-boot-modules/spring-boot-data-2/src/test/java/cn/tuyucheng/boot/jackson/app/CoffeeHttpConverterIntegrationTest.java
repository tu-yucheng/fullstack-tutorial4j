package cn.tuyucheng.boot.jackson.app;

import cn.tuyucheng.boot.jackson.config.CoffeeHttpConverterConfiguration;
import org.springframework.context.annotation.Import;

@Import(CoffeeHttpConverterConfiguration.class)
class CoffeeHttpConverterIntegrationTest extends AbstractCoffeeIntegrationTest {
    
}