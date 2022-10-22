package cn.tuyucheng.taketoday.wiring.configuration;

import cn.tuyucheng.taketoday.dependency.AnotherArbitraryDependency;
import cn.tuyucheng.taketoday.dependency.ArbitraryDependency;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextTestInjectQualifier {

    @Bean
    public ArbitraryDependency defaultFile() {
        return new ArbitraryDependency();
    }

    @Bean
    public ArbitraryDependency namedFile() {
        return new AnotherArbitraryDependency();
    }
}