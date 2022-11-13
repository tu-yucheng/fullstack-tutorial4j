package cn.tuyucheng.taketoday.easymock;

import org.easymock.EasyMock;
import org.easymock.IArgumentMatcher;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class ArgumentMatchersUnitTest {

	private final UserService userService = mock(UserService.class);

	//====================== equals

	@Test
	void givenUserService_whenAddNewUser_thenOK() {
		expect(userService.addUser(eq(new User()))).andReturn(true);
		replay(userService);

		boolean result = userService.addUser(new User());

		verify(userService);
		assertTrue(result);
	}

	//================ same

	@Test
	void givenUserService_whenAddSpecificUser_thenOK() {
		User user = new User();
		expect(userService.addUser(same(user))).andReturn(true);
		replay(userService);

		boolean result = userService.addUser(user);

		verify(userService);
		assertTrue(result);
	}

	//============= anyX

	@Test
	void givenUserService_whenSearchForUserByEmail_thenFound() {
		expect(userService.findByEmail(anyString())).andReturn(Collections.emptyList());
		replay(userService);

		List<User> result = userService.findByEmail("test@example.com");
		verify(userService);

		assertEquals(0, result.size());
	}

	//================= isA

	@Test
	void givenUserService_whenAddUser_thenOK() {
		expect(userService.addUser(isA(User.class))).andReturn(true);
		replay(userService);

		boolean result = userService.addUser(new User());

		verify(userService);
		assertTrue(result);
	}

	//=================== null, not null

	@Test
	void givenUserService_whenAddNull_thenFail() {
		expect(userService.addUser(isNull())).andReturn(false);
		replay(userService);

		boolean result = userService.addUser(null);
		verify(userService);
		assertFalse(result);
	}

	@Test
	void givenUserService_whenAddNotNull_thenOK() {
		expect(userService.addUser(notNull())).andReturn(true);
		replay(userService);

		boolean result = userService.addUser(new User());
		verify(userService);
		assertTrue(result);
	}

	// number less,great
	@Test
	void givenUserService_whenSearchForUserByAgeLess_thenFound() {
		expect(userService.findByAge(lt(100.0)))
				.andReturn(Collections.emptyList());
		replay(userService);

		List<User> result = userService.findByAge(20);
		verify(userService);

		assertEquals(0, result.size());
	}

	@Test
	void givenUserService_whenSearchForUserByAgeGreaterThen_thenFound() {
		expect(userService.findByAge(gt(10.0)))
				.andReturn(Collections.emptyList());
		replay(userService);

		List<User> result = userService.findByAge(20);
		verify(userService);

		assertEquals(0, result.size());
	}

	//=============== string
	//=============== start
	@Test
	void givenUserService_whenSearchForUserByEmailStartsWith_thenFound() {
		expect(userService.findByEmail(startsWith("test")))
				.andReturn(Collections.emptyList());
		replay(userService);

		List<User> result = userService.findByEmail("test@example.com");
		verify(userService);
		assertEquals(0, result.size());
	}

	//==================end
	@Test
	void givenUserService_whenSearchForUserByEmailEndsWith_thenFound() {
		expect(userService.findByEmail(endsWith("@example.com")))
				.andReturn(Collections.emptyList());
		replay(userService);

		List<User> result = userService.findByEmail("test@example.com");
		verify(userService);
		assertEquals(0, result.size());
	}

	//=================contain
	@Test
	void givenUserService_whenSearchForUserByEmailContains_thenFound() {
		expect(userService.findByEmail(contains("example")))
				.andReturn(Collections.emptyList());
		replay(userService);

		List<User> result = userService.findByEmail("test@example.com");
		verify(userService);
		assertEquals(0, result.size());
	}

	//==================matches
	@Test
	void givenUserService_whenSearchForUserByEmailMatches_thenFound() {
		expect(userService.findByEmail(matches(".+@.+..+")))
				.andReturn(Collections.emptyList());
		replay(userService);

		List<User> result = userService.findByEmail("test@exmpale.com");
		verify(userService);
		assertEquals(0, result.size());
	}

	//================== combine and, or, not
	@Test
	void givenUserService_whenSearchForUserByAgeRange_thenFound() {
		expect(userService.findByAge(and(gt(10.0), lt(100.0))))
				.andReturn(Collections.emptyList());
		replay(userService);

		List<User> result = userService.findByAge(20);
		verify(userService);
		assertEquals(0, result.size());
	}

	@Test
	void givenUserService_whenSearchForUserByEmailNotEndsWith_thenFound() {
		expect(userService.findByEmail(not(endsWith("com"))))
				.andReturn(Collections.emptyList());
		replay(userService);

		List<User> result = userService.findByEmail("test@example.org");
		verify(userService);
		assertEquals(0, result.size());
	}

	//================ custom matcher

	@Test
	void givenUserService_whenSearchForUserByEmailCharCount_thenFound() {
		expect(userService.findByEmail(minCharCount(5)))
				.andReturn(Collections.emptyList());
		replay(userService);

		List<User> result = userService.findByEmail("test@example.com");
		verify(userService);
		assertEquals(0, result.size());
	}

	public static String minCharCount(int count) {
		EasyMock.reportMatcher(new IArgumentMatcher() {
			@Override
			public boolean matches(Object argument) {
				return argument instanceof String && ((String) argument).length() >= count;
			}

			@Override
			public void appendTo(StringBuffer buffer) {
				buffer.append("minCharCount(").append(count).append(")");
			}
		});
		return null;
	}
}