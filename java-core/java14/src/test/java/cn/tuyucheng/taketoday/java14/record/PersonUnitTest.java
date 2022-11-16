package cn.tuyucheng.taketoday.java14.record;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonUnitTest {

	@Test
	void givenSameNameAndAddress_whenEquals_thenPersonsEqual() {
		String name = "John Doe";
		String address = "100 Linda Ln.";

		Person person1 = new Person(name, address);
		Person person2 = new Person(name, address);

		assertEquals(person1, person2);
	}

	@Test
	void givenDifferentObject_whenEquals_thenNotEqual() {
		Person person = new Person("John Doe", "100 Linda Ln.");

		assertNotEquals(person, new Object());
	}

	@Test
	void givenDifferentName_whenEquals_thenPersonsNotEqual() {
		String address = "100 Linda Ln.";

		Person person1 = new Person("Jane Doe", address);
		Person person2 = new Person("John Doe", address);

		assertNotEquals(person1, person2);
	}

	@Test
	void givenDifferentAddress_whenEquals_thenPersonsNotEqual() {
		String name = "John Doe";

		Person person1 = new Person(name, "100 Linda Ln.");
		Person person2 = new Person(name, "200 London Ave.");

		assertNotEquals(person1, person2);
	}

	@Test
	void givenSameNameAndAddress_whenHashCode_thenPersonsEqual() {
		String name = "John Doe";
		String address = "100 Linda Ln.";

		Person person1 = new Person(name, address);
		Person person2 = new Person(name, address);

		assertEquals(person1.hashCode(), person2.hashCode());
	}

	@Test
	void givenDifferentObject_whenHashCode_thenNotEqual() {
		Person person = new Person("John Doe", "100 Linda Ln.");

		assertNotEquals(person.hashCode(), new Object().hashCode());
	}

	@Test
	void givenDifferentName_whenHashCode_thenPersonsNotEqual() {
		String address = "100 Linda Ln.";

		Person person1 = new Person("Jane Doe", address);
		Person person2 = new Person("John Doe", address);

		assertNotEquals(person1.hashCode(), person2.hashCode());
	}

	@Test
	void givenDifferentAddress_whenHashCode_thenPersonsNotEqual() {
		String name = "John Doe";

		Person person1 = new Person(name, "100 Linda Ln.");
		Person person2 = new Person(name, "200 London Ave.");

		assertNotEquals(person1.hashCode(), person2.hashCode());
	}

	@Test
	void givenValidNameAndAddress_whenGetNameAndAddress_thenExpectedValuesReturned() {
		String name = "John Doe";
		String address = "100 Linda Ln.";

		Person person = new Person(name, address);

		assertEquals(name, person.name());
		assertEquals(address, person.address());
	}

	@Test
	void givenValidNameAndAddress_whenToString_thenCorrectStringReturned() {
		String name = "John Doe";
		String address = "100 Linda Ln.";

		Person person = new Person(name, address);

		assertEquals("Person[name=" + name + ", address=" + address + "]", person.toString());
	}

	@Test
	void givenNullName_whenConstruct_thenErrorThrown() {
		assertThrows(NullPointerException.class, () -> new Person(null, "100 Linda Ln."));
	}

	@Test
	void givenNullAddress_whenConstruct_thenErrorThrown() {
		assertThrows(NullPointerException.class, () -> new Person("John Doe", null));
	}

	@Test
	void givenUnknownAddress_whenConstructing_thenAddressPopulated() {
		String name = "John Doe";

		Person person = new Person(name);

		assertEquals(name, person.name());
		assertEquals(Person.UNKNOWN_ADDRESS, person.address());
	}

	@Test
	void givenUnnamed_whenConstructingThroughFactory_thenNamePopulated() {
		String address = "100 Linda Ln.";

		Person person = Person.unnamed(address);

		assertEquals(Person.UNNAMED, person.name());
		assertEquals(address, person.address());
	}
}