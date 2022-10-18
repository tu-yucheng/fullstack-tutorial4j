package cn.tuyucheng.boot.jackson.app;

import cn.tuyucheng.boot.jackson.config.CoffeeRegisterModuleConfig;
import org.springframework.context.annotation.Import;

@Import(CoffeeRegisterModuleConfig.class)
class CoffeeRegisterModuleIntegrationTest extends AbstractCoffeeIntegrationTest {

}