package cn.tuyucheng.taketoday.java14.recordvslombok;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RecordVsLombokUntTest {

	@Test
	void givenAColorRecord_hexStringIsCorrect() {
		var red = new ColorRecord(255, 0, 0);

		assertThat(red.getHexString()).isEqualTo("#FF0000");
	}

	@Test
	void givenAColorValueObject_hexStringIsCorrect() {
		var red = new ColorValueObject(255, 0, 0);

		assertThat(red.getHexString()).isEqualTo("#FF0000");
	}

	@Test
	void givenRecordWithManyAttributes_firstNameShouldBeJohn() {
		StudentRecord john = new StudentRecord("John", "Doe", null, "john@doe.com", null, null, "England", 20);

		assertThat(john.firstName()).isEqualTo("John");
	}

	@Test
	void givenBuilderWithManyAttributes_firstNameShouldBeJohn() {
		StudentBuilder john = StudentBuilder.builder()
				.firstName("John")
				.lastName("Doe")
				.email("john@doe.com")
				.country("England")
				.age(20)
				.build();

		assertThat(john.getFirstName()).isEqualTo("John");
	}
}