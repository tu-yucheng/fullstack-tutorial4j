package cn.tuyucheng.taketoday.staticvalue.injection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@PropertySource("/application.properties")
public class StaticValueApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaticValueApplication.class, args);
    }
}