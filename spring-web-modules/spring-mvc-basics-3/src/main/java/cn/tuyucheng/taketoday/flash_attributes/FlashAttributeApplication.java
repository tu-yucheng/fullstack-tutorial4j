package cn.tuyucheng.taketoday.flash_attributes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FlashAttributeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FlashAttributeApplication.class, args);
	}
}