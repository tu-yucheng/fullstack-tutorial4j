package cn.tuyucheng.taketoday.jackson.inheritance;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ItemIdRemovedFromUserUnitTest {

	@Test
	void givenRemoveItemJson_whenDeserialize_shouldHaveProperClassType() throws IOException {
		// given
		Event event = new ItemIdRemovedFromUser("1", 12345567L, "item_1", 2L);
		ObjectMapper objectMapper = new ObjectMapper();
		String eventJson = objectMapper.writeValueAsString(event);

		// when
		Event result = new ObjectMapper().readValue(eventJson, Event.class);

		// then
		assertTrue(result instanceof ItemIdRemovedFromUser);
		assertEquals("item_1", ((ItemIdRemovedFromUser) result).getItemId());
	}

	@Test
	void givenAddItemJson_whenSerialize_shouldIgnoreIdPropertyFromSuperclass() throws IOException {
		// given
		Event event = new ItemIdAddedToUser("1", 12345567L, "item_1", 2L);
		ObjectMapper objectMapper = new ObjectMapper();

		// when
		String eventJson = objectMapper.writeValueAsString(event);

		// then
		assertFalse(eventJson.contains("id"));
	}
}