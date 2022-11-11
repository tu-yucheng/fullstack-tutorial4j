package cn.tuyucheng.taketoday.wiring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class ApplicationContextTestResourceNameType {

	@Bean(name = "namedFile")
	public File namedFile() {
		return new File("namedFile.txt");
	}
}