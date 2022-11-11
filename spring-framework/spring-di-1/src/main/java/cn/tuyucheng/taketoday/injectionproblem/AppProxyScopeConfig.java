package cn.tuyucheng.taketoday.injectionproblem;

import cn.tuyucheng.taketoday.injectionproblem.prototype.PrototypeBean;
import cn.tuyucheng.taketoday.injectionproblem.singletone.SingletonBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("cn.tuyucheng.taketoday.injectionproblem")
public class AppProxyScopeConfig {

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public PrototypeBean prototypeBean() {
		return new PrototypeBean();
	}

	@Bean
	public SingletonBean singletonBean() {
		return new SingletonBean();
	}
}