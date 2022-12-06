package cn.tuyucheng.taketoday.systemrules;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SystemInUnitTest {

	@Test
	void givenTwoNames_whenSystemInMock_thenNamesJoinedTogether() throws Exception {
		withTextFromSystemIn("Jonathan", "Cook").execute(() ->
				assertEquals("Jonathan Cook", getFullname(), "Names should be concatenated"));
	}

	private String getFullname() {
		try (Scanner scanner = new Scanner(System.in)) {
			String firstName = scanner.next();
			String surname = scanner.next();
			return String.join(" ", firstName, surname);
		}
	}
}