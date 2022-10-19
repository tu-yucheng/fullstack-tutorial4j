package cn.tuyucheng;

import cn.tuyucheng.spring.MvcConfig;
import cn.tuyucheng.spring.PersistenceConfig;
import cn.tuyucheng.spring.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MvcConfig.class, PersistenceConfig.class, SecurityConfig.class})
@WebAppConfiguration
class SpringContextTest {

    @Test
    void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}