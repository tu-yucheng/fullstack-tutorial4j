package cn.tuyucheng.taketoday.spring.config;

import cn.tuyucheng.taketoday.spring.config.converter.StringToEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	public MvcConfig() {
		super();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToEnumConverter());
	}
}