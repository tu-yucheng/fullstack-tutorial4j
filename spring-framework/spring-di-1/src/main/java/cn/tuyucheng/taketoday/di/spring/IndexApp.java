package cn.tuyucheng.taketoday.di.spring;

public class IndexApp {

	private Service service;

	public String getServiceValue() {
		return service.serve();
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}