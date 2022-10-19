package cn.tuyucheng.taketoday.config;

import cn.tuyucheng.web.controller.handlermapping.BeanNameHandlerMappingController;
import cn.tuyucheng.web.controller.handlermapping.WelcomeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerMappingDefaultConfig {

    @Bean("/welcome")
    public BeanNameHandlerMappingController beanNameHandlerMapping() {
        return new BeanNameHandlerMappingController();
    }

    @Bean
    public WelcomeController welcomeDefaultMappingConfig() {
        return new WelcomeController();
    }
}