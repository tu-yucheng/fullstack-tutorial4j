package cn.tuyucheng.taketoday.java14.patternmatchingforinstanceof;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PatternMatchingForInstanceOfUnitTest {

	@Test
	void givenAnAnimal_whenTypeIsCat_ThenCatGoesMeow() {
		PatternMatchingForInstanceOf.Cat animal = mock(PatternMatchingForInstanceOf.Cat.class);

		PatternMatchingForInstanceOf instanceOf = new PatternMatchingForInstanceOf();
		instanceOf.performAnimalOperations(animal);

		verify(animal).meow();
	}

	@Test
	void givenAnAnimal_whenTypeIsDog_ThenDogGoesWoof() {
		PatternMatchingForInstanceOf.Dog animal = mock(PatternMatchingForInstanceOf.Dog.class);

		PatternMatchingForInstanceOf instanceOf = new PatternMatchingForInstanceOf();
		instanceOf.performAnimalOperations(animal);

		verify(animal).woof();
	}
}