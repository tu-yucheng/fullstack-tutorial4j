package cn.tuyucheng.taketoday.jackson.optionalwithjackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;

class OptionalTypeUnitTest {

	ObjectMapper mapper = new ObjectMapper().registerModule(new Jdk8Module());

	@Test
	void givenPresentOptional_whenSerializing_thenValueInJson() throws JsonProcessingException {
		String subTitle = "The Parish Boy's Progress";
		Book book = new Book();
		book.setTitle("Oliver Twist");
		book.setSubTitle(Optional.of(subTitle));

		String result = mapper.writeValueAsString(book);

		assertThat(from(result).getString("subTitle")).isEqualTo(subTitle);
	}

	@Test
	void givenEmptyOptional_whenSerializing_thenNullValue() throws JsonProcessingException {
		Book book = new Book();
		book.setTitle("Oliver Twist");
		book.setSubTitle(Optional.empty());

		String result = mapper.writeValueAsString(book);

		assertThat(from(result).getString("subTitle")).isNull();
	}

	@Test
	void givenField_whenDeserializingIntoOptional_thenIsPresentWithValue() throws IOException {
		String subTitle = "The Parish Boy's Progress";
		String book = "{ \"title\": \"Oliver Twist\", \"subTitle\": \"" + subTitle + "\" }";

		Book result = mapper.readValue(book, Book.class);

		assertThat(result.getSubTitle()).isEqualTo(Optional.of(subTitle));
	}

	@Test
	void givenNullField_whenDeserializingIntoOptional_thenIsEmpty() throws IOException {
		String book = "{ \"title\": \"Oliver Twist\", \"subTitle\": null }";

		Book result = mapper.readValue(book, Book.class);

		assertThat(result.getSubTitle()).isEmpty();
	}
}