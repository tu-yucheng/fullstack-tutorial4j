package cn.tuyucheng.taketoday.jackson.jacksonvsgson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActorJackson {

	private String imdbId;
	private Date dateOfBirth;
	private List<String> filmography;

	@Override
	public String toString() {
		return "ActorJackson [imdbId=" + imdbId + ", dateOfBirth=" + formatDateOfBirth() + ", filmography=" + filmography + "]";
	}

	private String formatDateOfBirth() {
		final DateFormat formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", Locale.US);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		return formatter.format(dateOfBirth);
	}
}