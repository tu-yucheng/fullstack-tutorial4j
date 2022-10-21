package cn.tuyucheng.taketoday.injectionproblem;

import cn.tuyucheng.taketoday.injectionproblem.prototype.PrototypeBean;
import cn.tuyucheng.taketoday.injectionproblem.singletone.SingletonBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.springframework.util.Assert.isTrue;

public class BeanInjectionStarter {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SingletonBean firstSingleton = context.getBean(SingletonBean.class);
        PrototypeBean firstPrototype = firstSingleton.getPrototypeBean();

        SingletonBean secondSingleton = context.getBean(SingletonBean.class);
        PrototypeBean secondPrototype = secondSingleton.getPrototypeBean();

        isTrue(firstPrototype.equals(secondPrototype), "The same instance is returned");
    }
}