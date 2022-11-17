package cn.tuyucheng.taketoday.java14.newfeatues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecordUnitTest {

	@SuppressWarnings("preview")
	record User(int id, String password) {
	}

	private User user1 = new User(0, "UserOne");

	@Test
	void givenRecord_whenObjInitialized_thenValuesCanBeFetchedWithGetters() {
		assertEquals(0, user1.id());
		assertEquals("UserOne", user1.password());
	}

	@Test
	void whenRecord_thenEqualsImplemented() {
		User user2 = user1;

		assertEquals(user1, user2);
	}

	@Test
	void whenRecord_thenToStringImplemented() {
		assertTrue(user1.toString().contains("UserOne"));
	}
}