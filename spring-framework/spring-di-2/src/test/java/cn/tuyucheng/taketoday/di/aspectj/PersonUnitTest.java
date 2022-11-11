package cn.tuyucheng.taketoday.di.aspectj;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AspectJConfig.class)
class PersonUnitTest {

	@Test
	void givenUnmanagedObjects_whenInjectingIdService_thenIdValueIsCorrectlySet() {
		PersonObject personObject = new PersonObject("tuyucheng");
		personObject.generateId();

		assertEquals(1, personObject.getId());
		assertEquals("tuyucheng", personObject.getName());

		PersonEntity personEntity = new PersonEntity("tuyucheng");
		assertEquals(2, personEntity.getId());
		assertEquals("tuyucheng", personEntity.getName());
	}
}