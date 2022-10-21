package cn.tuyucheng.taketoday.injectionproblem;

import cn.tuyucheng.taketoday.injectionproblem.prototype.PrototypeBean;
import cn.tuyucheng.taketoday.injectionproblem.singletone.SingletonAppContextBean;
import cn.tuyucheng.taketoday.injectionproblem.singletone.SingletonBean;
import cn.tuyucheng.taketoday.injectionproblem.singletone.SingletonObjectFactoryBean;
import cn.tuyucheng.taketoday.injectionproblem.singletone.SingletonProviderBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("cn.tuyucheng.taketoday.injectionproblem")
public class AppConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }

    @Bean
    public SingletonBean singletonBean() {
        return new SingletonBean();
    }

    @Bean
    public SingletonProviderBean singletonProviderBean() {
        return new SingletonProviderBean();
    }

    @Bean
    public SingletonAppContextBean singletonAppContextBean() {
        return new SingletonAppContextBean();
    }

    @Bean
    public SingletonObjectFactoryBean singletonObjectFactoryBean() {
        return new SingletonObjectFactoryBean();
    }
}