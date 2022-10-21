package cn.tuyucheng.taketoday.injectionproblem.singletone;

import cn.tuyucheng.taketoday.injectionproblem.prototype.PrototypeBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

public class SingletonFunctionBean {

    @Autowired
    private Function<String, PrototypeBean> beanFactory;

    public PrototypeBean getPrototypeInstance(String name) {
        return beanFactory.apply(name);
    }
}