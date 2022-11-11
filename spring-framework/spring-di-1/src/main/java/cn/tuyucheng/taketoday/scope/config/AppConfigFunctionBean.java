package cn.tuyucheng.taketoday.scope.config;

import cn.tuyucheng.taketoday.injectionproblem.prototype.PrototypeBean;
import cn.tuyucheng.taketoday.injectionproblem.singletone.SingletonFunctionBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.function.Function;

@Configuration
public class AppConfigFunctionBean {

	@Bean
	public Function<String, PrototypeBean> beanFactory() {
		return this::prototypeBeanWithParam;
	}

	@Bean
	@Scope(value = "prototype")
	public PrototypeBean prototypeBeanWithParam(String name) {
		return new PrototypeBean(name);
	}

	@Bean
	public SingletonFunctionBean singletonFunctionBean() {
		return new SingletonFunctionBean();
	}
}