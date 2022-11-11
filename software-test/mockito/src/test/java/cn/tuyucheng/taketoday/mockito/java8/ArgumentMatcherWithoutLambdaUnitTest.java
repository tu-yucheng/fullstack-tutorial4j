package cn.tuyucheng.taketoday.mockito.java8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArgumentMatcherWithoutLambdaUnitTest {

	private static class PeterArgumentMatcher implements ArgumentMatcher<Person> {

		@Override
		public boolean matches(Person p) {
			return p.getName().equals("Peter");
		}
	}

	@InjectMocks
	private UnemploymentServiceImpl unemploymentService;

	@Mock
	private JobService jobService;

	@Test
	void whenPersonWithJob_thenIsNotEntitled() {
		Person peter = new Person("Peter");
		Person linda = new Person("Linda");

		JobPosition teacher = new JobPosition("Teacher");

		when(jobService.findCurrentJobPosition(
				ArgumentMatchers.argThat(new PeterArgumentMatcher()))
		).thenReturn(Optional.of(teacher));

		assertTrue(unemploymentService.personIsEntitledToUnemploymentSupport(linda));
		assertFalse(unemploymentService.personIsEntitledToUnemploymentSupport(peter));
	}
}