package cn.tuyucheng.taketoday.spring.jms;

public class Employee {
	private final String name;
	private final Integer age;

	public Employee(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public String toString() {
		return "Employee: name(" + name + "), age(" + age + ")";
	}
}