package cn.tuyucheng.taketoday.interpolation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NotNullRequest {

	@NotNull(message = "stringValue has to be present")
	private String stringValue;
}