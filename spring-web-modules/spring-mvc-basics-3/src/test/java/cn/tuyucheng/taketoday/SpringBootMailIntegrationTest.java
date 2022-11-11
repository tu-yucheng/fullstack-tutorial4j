package cn.tuyucheng.taketoday;

import cn.tuyucheng.taketoday.boot.SpringMvcApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringMvcApplication.class)
class SpringBootMailIntegrationTest {
	@Autowired
	private JavaMailSender javaMailSender;

	private Wiser wiser;

	private final String userTo = "user2@localhost";
	private final String userFrom = "user1@localhost";
	private final String subject = "Test subject";
	private final String textMail = "Text subject mail";

	@BeforeEach
	void setUp() throws Exception {
		final int TEST_PORT = 8025;
		wiser = new Wiser(TEST_PORT);
		wiser.start();
	}

	@AfterEach
	void tearDown() throws Exception {
		wiser.stop();
	}

	@Test
	void givenMail_whenSendAndReceived_thenCorrect() throws Exception {
		SimpleMailMessage message = composeEmailMessage();
		javaMailSender.send(message);
		List<WiserMessage> messages = wiser.getMessages();

		assertThat(messages, hasSize(1));
		WiserMessage wiserMessage = messages.get(0);
		assertEquals(userFrom, wiserMessage.getEnvelopeSender());
		assertEquals(userTo, wiserMessage.getEnvelopeReceiver());
		assertEquals(subject, getSubject(wiserMessage));
		assertEquals(textMail, getMessage(wiserMessage));
	}

	private SimpleMailMessage composeEmailMessage() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userTo);
		mailMessage.setReplyTo(userFrom);
		mailMessage.setFrom(userFrom);
		mailMessage.setSubject(subject);
		mailMessage.setText(textMail);
		return mailMessage;
	}

	private String getSubject(WiserMessage wiserMessage) throws MessagingException {
		return wiserMessage.getMimeMessage().getSubject();
	}

	private String getMessage(WiserMessage wiserMessage) throws MessagingException, IOException {
		return wiserMessage.getMimeMessage().getContent().toString().trim();
	}
}