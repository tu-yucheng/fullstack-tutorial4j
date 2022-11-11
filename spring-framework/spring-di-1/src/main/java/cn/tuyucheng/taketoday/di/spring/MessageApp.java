package cn.tuyucheng.taketoday.di.spring;

public class MessageApp {

	private final Service service;

	public MessageApp(Service service) {
		this.service = service;
	}

	public String getServiceValue() {
		return service.serve();
	}
}