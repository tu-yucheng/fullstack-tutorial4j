package cn.tuyucheng.taketoday.injectionproblem.singletone;

import cn.tuyucheng.taketoday.injectionproblem.prototype.PrototypeBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

@Slf4j
public class SingletonBean {

    @Autowired
    private PrototypeBean prototypeBean;

    public SingletonBean() {
        log.info("Singleton instance created");
    }

    public PrototypeBean getPrototypeBean() {
        log.info(String.valueOf(LocalTime.now()));
        return prototypeBean;
    }
}