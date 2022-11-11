package cn.tuyucheng.acl;

import cn.tuyucheng.acl.persistence.dao.NoticeMessageRepository;
import cn.tuyucheng.acl.persistence.entity.NoticeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@TestExecutionListeners(listeners = {ServletTestExecutionListener.class,
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		WithSecurityContextTestExecutionListener.class
})
class SpringACLIntegrationTest extends AbstractJUnit4SpringContextTests {

	private static final Integer FIRST_MESSAGE_ID = 1;
	private static final Integer SECOND_MESSAGE_ID = 2;
	private static final String EDITED_CONTENT = "EDITED";

	@Configuration
	@ComponentScan("cn.tuyucheng.acl.*")
	public static class SpringConfig {

	}

	@Autowired
	NoticeMessageRepository noticeMessageRepository;

	@Test
	@WithMockUser(username = "manager")
	void givenUserManager_whenFindAllMessage_thenReturnFirstMessage() {
		List<NoticeMessage> details = noticeMessageRepository.findAll();
		assertNotNull(details);
		assertEquals(1, details.size());
		assertEquals(FIRST_MESSAGE_ID, details.get(0).getId());
	}

	@Test
	@WithMockUser(username = "manager")
	void givenUserManager_whenFind1stMessageByIdAndUpdateItsContent_thenOK() {
		NoticeMessage firstMessage = noticeMessageRepository.findById(FIRST_MESSAGE_ID);
		assertNotNull(firstMessage);
		assertEquals(FIRST_MESSAGE_ID, firstMessage.getId());

		firstMessage.setContent(EDITED_CONTENT);
		noticeMessageRepository.save(firstMessage);

		NoticeMessage editedFirstMessage = noticeMessageRepository.findById(FIRST_MESSAGE_ID);
		assertNotNull(editedFirstMessage);
		assertEquals(FIRST_MESSAGE_ID, editedFirstMessage.getId());
		assertEquals(EDITED_CONTENT, editedFirstMessage.getContent());
	}

	@Test
	@WithMockUser(username = "hr")
	void givenUsernameHr_whenFindMessageById2_thenOK() {
		NoticeMessage secondMessage = noticeMessageRepository.findById(SECOND_MESSAGE_ID);
		assertNotNull(secondMessage);
		assertEquals(SECOND_MESSAGE_ID, secondMessage.getId());
	}

	@Test
	@WithMockUser(username = "hr")
	void givenUsernameHr_whenUpdateMessageWithId2_thenFail() {
		NoticeMessage secondMessage = new NoticeMessage();
		secondMessage.setId(SECOND_MESSAGE_ID);
		secondMessage.setContent(EDITED_CONTENT);
		assertThrows(AccessDeniedException.class, () -> noticeMessageRepository.save(secondMessage));
	}

	@Test
	@WithMockUser(roles = {"EDITOR"})
	void givenRoleEditor_whenFindAllMessage_thenReturn3Message() {
		List<NoticeMessage> details = noticeMessageRepository.findAll();
		assertNotNull(details);
		assertEquals(3, details.size());
	}

	@Test
	@WithMockUser(roles = {"EDITOR"})
	void givenRoleEditor_whenUpdateThirdMessage_thenOK() {
		NoticeMessage thirdMessage = new NoticeMessage();
		Integer THIRD_MESSAGE_ID = 3;
		thirdMessage.setId(THIRD_MESSAGE_ID);
		thirdMessage.setContent(EDITED_CONTENT);
		noticeMessageRepository.save(thirdMessage);
	}

	@Test
	@WithMockUser(roles = {"EDITOR"})
	void givenRoleEditor_whenFind1stMessageByIdAndUpdateContent_thenFail() {
		NoticeMessage firstMessage = noticeMessageRepository.findById(FIRST_MESSAGE_ID);
		assertNotNull(firstMessage);
		assertEquals(FIRST_MESSAGE_ID, firstMessage.getId());
		firstMessage.setContent(EDITED_CONTENT);
		assertThrows(AccessDeniedException.class, () -> noticeMessageRepository.save(firstMessage));
	}
}