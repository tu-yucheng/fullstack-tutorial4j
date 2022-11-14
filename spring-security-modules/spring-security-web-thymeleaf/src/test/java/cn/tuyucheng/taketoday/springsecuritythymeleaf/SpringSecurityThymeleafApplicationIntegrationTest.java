package cn.tuyucheng.taketoday.springsecuritythymeleaf;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringSecurityThymeleafApplicationIntegrationTest {

	@Autowired
	ViewController viewController;
	@Autowired
	WebApplicationContext wac;

	@Test
	void whenConfigured_thenLoadsContext() {
		assertNotNull(viewController);
		assertNotNull(wac);
	}
}