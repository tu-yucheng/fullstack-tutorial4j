package cn.tuyucheng.taketoday.pointcutadvice;

import cn.tuyucheng.taketoday.Application;
import cn.tuyucheng.taketoday.pointcutadvice.dao.FooDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Application.class}, loader = AnnotationConfigContextLoader.class)
class AopLoggingIntegrationTest {

	@Autowired
	private FooDao dao;
	private List<String> messages;

	@BeforeEach
	void setUp() {
		messages = new ArrayList<>();

		Handler logEventHandler = new Handler() {
			@Override
			public void publish(LogRecord record) {
				messages.add(record.getMessage());
			}

			@Override
			public void flush() {
			}

			@Override
			public void close() throws SecurityException {
			}
		};

		Logger logger = Logger.getLogger(LoggingAspect.class.getName());
		logger.addHandler(logEventHandler);
	}

	@Test
	void givenLoggingAspect_whenCallDaoMethod_thenBeforeAdviceIsCalled() {
		dao.findById(1L);
		assertThat(messages, hasSize(1));

		String logMessage = messages.get(0);
		Pattern pattern = Pattern.compile("^\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}:\\d{3}]findById$");
		assertTrue(pattern.matcher(logMessage).matches());
	}

	@Test
	void givenLoggingAspect_whenCallLoggableAnnotatedMethod_thenMethodIsLogged() {
		dao.create(42L, "baz");
		assertThat(messages, hasItem("Executing method: create"));
	}

	@Test
	void givenLoggingAspect_whenCallMethodAcceptingAnnotatedArgument_thenArgumentIsLogged() {
		Foo foo = new Foo(42L, "baz");
		dao.merge(foo);
		assertThat(messages, hasItem("Accepting beans with @Entity annotation: " + foo));
	}
}