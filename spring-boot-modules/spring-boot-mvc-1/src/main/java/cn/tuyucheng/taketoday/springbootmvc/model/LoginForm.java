package cn.tuyucheng.taketoday.springbootmvc.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class LoginForm {

	@NotEmpty(message = "{email.notempty}")
	@Email
	private String email;

	@NotNull
	private String password;
}