package cn.tuyucheng.taketoday.spring.jms;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class DefaultTextMessageSenderIntegrationTest {

	private static SampleJmsMessageSender messageProducer;
	private static SampleListener messageListener;

	@SuppressWarnings("resource")
	@BeforeAll
	static void setUp() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:EmbeddedActiveMQ.xml", "classpath:applicationContext.xml");
		messageProducer = (SampleJmsMessageSender) applicationContext.getBean("SampleJmsMessageSender");
		messageListener = (SampleListener) applicationContext.getBean("messageListener");
	}

	@Test
	void testSimpleSend() {
		messageProducer.simpleSend();
	}

	@Test
	void testSendTextMessage() {
		messageProducer.sendTextMessage(null);
	}
}