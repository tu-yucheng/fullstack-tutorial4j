package cn.tuyucheng.taketoday.collection;

public class TuyuchengBean {

	private final String name;

	public TuyuchengBean(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}