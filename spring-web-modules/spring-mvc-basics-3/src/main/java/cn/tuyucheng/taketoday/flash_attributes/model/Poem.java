package cn.tuyucheng.taketoday.flash_attributes.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Setter
public class Poem {
	private String title;
	private String author;
	private String body;

	public static boolean isValidPoem(Poem poem) {
		return poem != null && Strings.isNotBlank(poem.getAuthor()) && Strings.isNotBlank(poem.getBody()) && Strings.isNotBlank(poem.getTitle());
	}
}