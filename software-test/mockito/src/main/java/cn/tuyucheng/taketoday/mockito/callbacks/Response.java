package cn.tuyucheng.taketoday.mockito.callbacks;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
	private Data data;
	private boolean isValid = true;
}