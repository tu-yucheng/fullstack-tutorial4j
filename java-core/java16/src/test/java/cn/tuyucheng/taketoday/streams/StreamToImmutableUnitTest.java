package cn.tuyucheng.taketoday.streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StreamToImmutableUnitTest {

	@Test
	void whenUsingStreamToList_thenReturnImmutableList() {
		List<String> immutableList = Stream.of("a", "b", "c", "d")
				.toList();

		assertThrows(UnsupportedOperationException.class, () -> immutableList.add("e"));
	}
}