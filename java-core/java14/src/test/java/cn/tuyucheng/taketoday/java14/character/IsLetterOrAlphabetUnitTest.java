package cn.tuyucheng.taketoday.java14.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsLetterOrAlphabetUnitTest {

	@Test
	void givenACharacter_whenLetter_thenAssertIsLetterTrue() {
		assertTrue(Character.isLetter(65));
	}

	@Test
	void givenACharacter_whenLetter_thenAssertIsAlphabeticTrue() {
		assertTrue(Character.isAlphabetic(65));
	}

	@Test
	void givenACharacter_whenAlphabeticAndNotLetter_thenAssertIsLetterFalse() {
		assertFalse(Character.isLetter(837));
	}

	@Test
	void givenACharacter_whenAlphabeticAndNotLetter_thenAssertIsAlphabeticTrue() {
		assertTrue(Character.isAlphabetic(837));
	}
}