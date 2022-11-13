package cn.tuyucheng.taketoday.mockito;

import cn.tuyucheng.taketoday.testcase.LoginController;
import cn.tuyucheng.taketoday.testcase.LoginDao;
import cn.tuyucheng.taketoday.testcase.LoginService;
import cn.tuyucheng.taketoday.testcase.UserForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class LoginControllerIntegrationTest {

	@Mock
	private LoginDao loginDao;

	@Spy
	@InjectMocks
	private LoginService spiedLoginService;

	@Mock
	private LoginService loginService;

	@InjectMocks
	private LoginController loginController;

	@BeforeEach
	void setUp() {
		loginController = new LoginController();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void assertThatNoMethodHasBeenCalled() {
		loginController.login(null);
		// no method called
		Mockito.verifyNoInteractions(loginService);
	}

	@Test
	void assertTwoMethodsHaveBeenCalled() {
		UserForm userForm = new UserForm();
		userForm.username = "foo";
		Mockito.when(loginService.login(userForm))
				.thenReturn(true);

		String login = loginController.login(userForm);

		assertEquals("OK", login);
		verify(loginService)
				.login(userForm);
		verify(loginService)
				.setCurrentUser("foo");
	}

	@Test
	void assertOnlyOneMethodHasBeenCalled() {
		UserForm userForm = new UserForm();
		userForm.username = "foo";
		Mockito.when(loginService.login(userForm))
				.thenReturn(false);

		String login = loginController.login(userForm);

		assertEquals("KO", login);
		verify(loginService)
				.login(userForm);
		verifyNoMoreInteractions(loginService);
	}

	@Test
	void mockExceptionThrowing() {
		UserForm userForm = new UserForm();
		Mockito.when(loginService.login(userForm))
				.thenThrow(IllegalArgumentException.class);

		String login = loginController.login(userForm);

		assertEquals("ERROR", login);
		verify(loginService)
				.login(userForm);
		Mockito.verifyNoMoreInteractions(loginService);
	}

	@Test
	void mockAnObjectToPassAround() {
		UserForm userForm = Mockito.when(Mockito.mock(UserForm.class)
						.getUsername())
				.thenReturn("foo")
				.getMock();
		Mockito.when(loginService.login(userForm))
				.thenReturn(true);

		String login = loginController.login(userForm);

		assertEquals("OK", login);
		verify(loginService)
				.login(userForm);
		verify(loginService)
				.setCurrentUser("foo");
	}

	@Test
	void argumentMatching() {
		UserForm userForm = new UserForm();
		userForm.username = "foo";
		// default matcher
		Mockito.when(loginService.login(Mockito.any(UserForm.class)))
				.thenReturn(true);

		String login = loginController.login(userForm);

		assertEquals("OK", login);
		Mockito.verify(loginService)
				.login(userForm);
		// complex matcher
		Mockito.verify(loginService)
				.setCurrentUser(ArgumentMatchers.argThat(new ArgumentMatcher<String>() {
					@Override
					public boolean matches(String argument) {
						return argument.startsWith("foo");
					}
				}));
	}

	@Test
	void partialMocking() {
		// use partial mock
		loginController.loginService = spiedLoginService;
		UserForm userForm = new UserForm();
		userForm.username = "foo";
		// let service's login use implementation so let's mock DAO call
		Mockito.when(loginDao.login(userForm))
				.thenReturn(1);

		String login = loginController.login(userForm);

		assertEquals("OK", login);
		// verify mocked call
		verify(spiedLoginService)
				.setCurrentUser("foo");
	}
}