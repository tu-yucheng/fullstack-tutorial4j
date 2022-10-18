package cn.tuyucheng.boot.jackson.app;

import cn.tuyucheng.boot.jackson.config.CoffeeCustomizerConfig;
import org.springframework.context.annotation.Import;

@Import(CoffeeCustomizerConfig.class)
class CoffeeCustomizerIntegrationTest extends AbstractCoffeeIntegrationTest {
    
}