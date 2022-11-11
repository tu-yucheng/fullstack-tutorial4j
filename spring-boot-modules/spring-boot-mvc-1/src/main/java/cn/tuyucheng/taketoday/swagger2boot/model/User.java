package cn.tuyucheng.taketoday.swagger2boot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class User {

	@Id
	private Long id;

	@NotNull(message = "First Name cannot be null")
	private String firstName;

	@Min(value = 15, message = "Age should not be less than 15")
	@Max(value = 65, message = "Age should not be greater than 65")
	private int age;

	@Email(regexp = ".*@.*\\..*", message = "Email should be valid")
	private String email;
}