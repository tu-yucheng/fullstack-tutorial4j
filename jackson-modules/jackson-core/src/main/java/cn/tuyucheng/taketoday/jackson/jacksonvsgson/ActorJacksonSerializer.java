package cn.tuyucheng.taketoday.jackson.jacksonvsgson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ActorJacksonSerializer extends StdSerializer<ActorJackson> {

	private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public ActorJacksonSerializer(Class t) {
		super(t);
	}

	@Override
	public void serialize(ActorJackson actor, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("imdbId", actor.getImdbId());
		jsonGenerator.writeObjectField("dateOfBirth", actor.getDateOfBirth() != null ? sdf.format(actor.getDateOfBirth()) : null);
		jsonGenerator.writeNumberField("N° Film: ", actor.getFilmography() != null ? actor.getFilmography()
				.size() : null);
		jsonGenerator.writeStringField("filmography", String.join("-", actor.getFilmography()));
		jsonGenerator.writeEndObject();
	}
}