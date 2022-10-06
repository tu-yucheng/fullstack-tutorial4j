package cn.tuyucheng.taketoday.mockito.java8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomAnswerWithoutLambdaUnitTest {

    private static class PersonAnswer implements Answer<Stream<JobPosition>> {

        @Override
        public Stream<JobPosition> answer(InvocationOnMock invocation) {
            Person person = invocation.getArgument(0);

            if (person.getName().equals("Peter")) {
                return Stream.<JobPosition>builder().add(new JobPosition("Teacher")).build();
            }

            return Stream.empty();
        }
    }

    @InjectMocks
    private UnemploymentServiceImpl unemploymentService;

    @Mock
    private JobService jobService;

    @Test
    void whenPersonWithJobHistory_thenSearchReturnsValue() {
        Person peter = new Person("Peter");

        assertEquals("Teacher", unemploymentService.searchJob(peter, "").get().getTitle());
    }

    @Test
    void whenPersonWithNoJobHistory_thenSearchReturnsEmpty() {
        Person linda = new Person("Linda");

        assertFalse(unemploymentService.searchJob(linda, "").isPresent());
    }

    @BeforeEach
    void init() {
        when(jobService.listJobs(any(Person.class))).then(new PersonAnswer());
    }
}