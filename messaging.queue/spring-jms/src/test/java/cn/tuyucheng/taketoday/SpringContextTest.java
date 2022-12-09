package cn.tuyucheng.taketoday;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
class SpringContextTest {

	@Test
	void whenSpringContextIsBootstrapped_thenNoExceptions() {
	}
}