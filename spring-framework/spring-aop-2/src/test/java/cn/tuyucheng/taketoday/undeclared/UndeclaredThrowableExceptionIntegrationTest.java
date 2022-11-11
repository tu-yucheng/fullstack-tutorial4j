package cn.tuyucheng.taketoday.undeclared;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.UndeclaredThrowableException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UndeclaredApplication.class)
class UndeclaredThrowableExceptionIntegrationTest {

	@Autowired
	private UndeclaredService service;

	@Test
	void givenAnAspect_whenCallingAdvisedMethod_thenShouldWrapTheException() {
		assertThatThrownBy(service::doSomething)
				.isInstanceOf(UndeclaredThrowableException.class)
				.hasCauseInstanceOf(SomeCheckedException.class);
	}
}