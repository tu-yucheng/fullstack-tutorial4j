package cn.tuyucheng.taketoday.injectionproblem.singletone;

import cn.tuyucheng.taketoday.injectionproblem.prototype.PrototypeBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Provider;

public class SingletonProviderBean {

    @Autowired
    private Provider<PrototypeBean> myPrototypeBeanProvider;

    public PrototypeBean getPrototypeInstance() {
        return myPrototypeBeanProvider.get();
    }
}