package cn.tuyucheng.taketoday.pointcutadvice;

import cn.tuyucheng.taketoday.Application;
import cn.tuyucheng.taketoday.pointcutadvice.dao.FooDao;
import cn.tuyucheng.taketoday.pointcutadvice.events.FooCreationEventListener;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Application.class}, loader = AnnotationConfigContextLoader.class)
class AopPublishingIntegrationTest {

	@Autowired
	private FooDao dao;
	private Handler logEventHandler;
	private List<String> messages;

	@BeforeEach
	void setUp() {
		logEventHandler = new Handler() {
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

		messages = new ArrayList<>();
	}

	@Test
	void givenPublishingAspect_whenCallCreate_thenCreationEventIsPublished() {
		Logger logger = Logger.getLogger(FooCreationEventListener.class.getName());
		logger.addHandler(logEventHandler);

		dao.create(1L, "Bar");

		String logMessage = messages.get(0);
		Pattern pattern = Pattern.compile("Created foo instance: " + Pattern.quote(new Foo(1L, "Bar").toString()));
		assertTrue(pattern.matcher(logMessage).matches());
	}
}