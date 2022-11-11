package cn.tuyucheng.taketoday.spring.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer {
	private String customerId;
	private String customerName;
	private String customerContact;
	private String customerEmail;
}