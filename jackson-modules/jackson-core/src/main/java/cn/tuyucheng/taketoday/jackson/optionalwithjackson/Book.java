package cn.tuyucheng.taketoday.jackson.optionalwithjackson;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class Book {

	private String title;
	private Optional<String> subTitle;
}