package cn.tuyucheng.taketoday.jackson.jacksonvsgson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JacksonSerializeUnitTest {

	@Test
	void whenSimpleSerialize_thenCorrect() throws JsonProcessingException, ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		final ActorJackson rudyYoungblood = new ActorJackson(
				"nm2199632",
				sdf.parse("21-09-1982"),
				List.of("Apocalypto", "Beatdown", "Wind Walkers"));
		final Movie movie = new Movie(
				"tt0472043",
				"Mel Gibson",
				List.of(rudyYoungblood));

		final ObjectMapper mapper = new ObjectMapper();
		final String jsonResult = mapper.writeValueAsString(movie);

		final String expectedOutput = "{\"imdbId\":\"tt0472043\",\"director\":\"Mel Gibson\",\"actors\":[{\"imdbId\":\"nm2199632\",\"dateOfBirth\":401414400000,\"filmography\":[\"Apocalypto\",\"Beatdown\",\"Wind Walkers\"]}]}";
		assertEquals(jsonResult, expectedOutput);
	}

	@Test
	void whenCustomSerialize_thenCorrect() throws ParseException, IOException {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		final ActorJackson rudyYoungblood = new ActorJackson(
				"nm2199632",
				sdf.parse("21-09-1982"),
				List.of("Apocalypto", "Beatdown", "Wind Walkers"));
		final MovieWithNullValue movieWithNullValue = new MovieWithNullValue(
				null,
				"Mel Gibson",
				List.of(rudyYoungblood));

		final SimpleModule module = new SimpleModule();
		module.addSerializer(new ActorJacksonSerializer(ActorJackson.class));
		final ObjectMapper mapper = new ObjectMapper();

		final String jsonResult = mapper.registerModule(module)
				.writer(new DefaultPrettyPrinter())
				.writeValueAsString(movieWithNullValue);

		final Object json = mapper.readValue("""
				{
				    "imdbId": null,
				    "actors": [
				        {
				            "imdbId": "nm2199632",
				            "dateOfBirth": "21-09-1982",
				            "N° Film: ": 3,
				            "filmography": "Apocalypto-Beatdown-Wind Walkers"
				        }
				    ]
				}""", Object.class);
		final String expectedOutput = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(json);

		assertEquals(jsonResult, expectedOutput);
	}
}