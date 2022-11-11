package cn.tuyucheng.taketoday.mockito.argumentcaptor;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email {

	private String address;
	private String subject;
	private String body;
	private Format format;

	public Email(String address, String subject, String body) {
		this.address = address;
		this.subject = subject;
		this.body = body;
	}
}