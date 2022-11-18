package cn.tuyucheng.taketoday.streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamToListComparisonWithCollectorsToListUnitTest {

	@Test
	void whenAddingtoCollectToList_thenSuccess() {
		List<String> result = Stream.of(Locale.getISOCountries())
				.collect(Collectors.toList());

		assertDoesNotThrow(() -> {
			result.add("test");
		});
	}

	@Test
	void whenSortingtoCollectToList_thenSuccess() {
		List<String> result = Stream.of(Locale.getISOCountries())
				.collect(Collectors.toList());

		assertDoesNotThrow(() -> result.sort(String::compareToIgnoreCase));
	}

	@Test
	void whenAddingCollectUnmodifiableToList_thenException() {
		List<String> result = Stream.of(Locale.getISOCountries())
				.collect(Collectors.toUnmodifiableList());

		assertThrows(UnsupportedOperationException.class, () -> result.add("test"));
	}

	@Test
	void whenSortingCollectUnmodifiableToList_thenSortException() {
		List<String> result = Stream.of(Locale.getISOCountries())
				.collect(Collectors.toUnmodifiableList());

		assertThrows(UnsupportedOperationException.class, () -> result.sort(String::compareToIgnoreCase));
	}

	@Test
	void whenAddingStreamToList_thenException() {
		List<String> result = Stream.of(Locale.getISOCountries())
				.toList();

		assertThrows(UnsupportedOperationException.class, () -> result.add("test"));
	}

	@Test
	void whenSortingStreamToList_thenException() {
		List<String> result = Stream.of(Locale.getISOCountries())
				.toList();

		assertThrows(UnsupportedOperationException.class, () -> result.sort(String::compareToIgnoreCase));
	}

	@Test
	void whenComparingStreamToList_withCollectToList_thenEqual() {

		List<String> streamToList = Stream.of(Locale.getISOCountries())
				.toList();
		List<String> collectToList = Stream.of(Locale.getISOCountries())
				.collect(Collectors.toList());

		assertEquals(streamToList, collectToList);
	}

	@Test
	void whenComparingStreamToList_withUnmodifiedCollectToList_thenEqual() {
		List<String> streamToList = Stream.of(Locale.getISOCountries())
				.toList();
		List<String> collectToUnmodifiableList = Stream.of(Locale.getISOCountries())
				.collect(Collectors.toUnmodifiableList());

		assertEquals(streamToList, collectToUnmodifiableList);
	}

	@Test
	void whenNullCollectorsToList_thenSuccess() {
		assertDoesNotThrow(() -> {
			Stream.of(null, null)
					.collect(Collectors.toList());
		});
	}

	@Test
	void whenNullCollectorsUnmodifiableToList_thenException() {
		assertThrows(NullPointerException.class, () -> Stream.of(null, null)
				.collect(Collectors.toUnmodifiableList()));
	}

	@Test
	void whenNullStreamToList_thenSuccess() {
		assertDoesNotThrow(() -> {
			Stream.of(null, null).toList();
		});
	}
}