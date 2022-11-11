package cn.tuyucheng.taketoday.junit5.testinstance;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class Tweet implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	private String id;
	private String content;
}