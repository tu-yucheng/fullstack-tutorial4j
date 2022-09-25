package cn.tuyucheng.taketoday.scope.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrototypeBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String name;

    public PrototypeBean() {
        logger.info("Prototype instance created");
    }

    public PrototypeBean(String name) {
        this.name = name;
        logger.info("Prototype instance " + name + " created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}