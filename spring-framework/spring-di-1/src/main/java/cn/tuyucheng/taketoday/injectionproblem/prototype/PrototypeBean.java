package cn.tuyucheng.taketoday.injectionproblem.prototype;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrototypeBean {
	private String name;

	public PrototypeBean() {
		log.info("Prototype instance created");
	}

	public PrototypeBean(String name) {
		this.name = name;
		log.info("Prototype instance " + name + " created");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}