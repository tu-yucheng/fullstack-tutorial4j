package cn.tuyucheng.taketoday.jackson.jacksonvsgson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie {

	private String imdbId;
	private String director;
	private List<ActorJackson> actors;

	@Override
	public String toString() {
		return "Movie [imdbId=" + imdbId + ", director=" + director + ", actors=" + actors + "]";
	}
}