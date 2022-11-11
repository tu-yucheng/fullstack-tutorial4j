package cn.tuyucheng.taketoday.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Employee {
	private long id;
	private String name;
	private String contactNumber;
	private String workingArea;
}