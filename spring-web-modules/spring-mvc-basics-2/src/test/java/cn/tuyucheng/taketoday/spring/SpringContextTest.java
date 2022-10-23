package cn.tuyucheng.taketoday.spring;

import cn.tuyucheng.taketoday.spring.configuration.ApplicationConfiguration;
import cn.tuyucheng.taketoday.spring.configuration.EmailConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, EmailConfiguration.class})
@WebAppConfiguration
class SpringContextTest {

    @Test
    void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}