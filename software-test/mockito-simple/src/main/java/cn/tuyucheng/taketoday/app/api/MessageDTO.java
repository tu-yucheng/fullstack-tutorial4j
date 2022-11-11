package cn.tuyucheng.taketoday.app.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
	private String from;
	private String to;
	private String text;
}