package cn.tuyucheng.taketoday.jackson.jacksonvsgson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class MovieWithNullValue {

	private String imdbId;

	@JsonIgnore
	private String director;

	private List<ActorJackson> actors;
}