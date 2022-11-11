package cn.tuyucheng.taketoday.di.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringMainConfig.class})
class SpringUnitTest {

	@Autowired
	ApplicationContext context;

	@Test
	void givenAccountServiceAutowiredToUserService_whenGetAccountServiceInvoked_thenReturnValueIsNotNull() {
		UserService userService = context.getBean(UserService.class);
		assertNotNull(userService.getAccountService());
	}

	@Test
	void givenBookServiceIsRegisteredAsBeanInContext_WhenBookServiceIsRetrievedFromContext_ThenReturnValueIsNotNull() {
		BookService bookService = context.getBean(BookService.class);
		assertNotNull(bookService);
	}

	@Test
	void givenBookServiceIsRegisteredAsBeanInContextByOverridingAudioBookService_WhenAudioBookServiceIsRetrievedFromContext_ThenNoSuchBeanDefinitionExceptionIsThrown() {
		BookService bookService = context.getBean(BookService.class);
		assertNotNull(bookService);
		AudioBookService audioBookService = context.getBean(AudioBookService.class);
		assertNotNull(audioBookService);
	}

	@Test
	void givenAuthorServiceAutowiredToBookServiceAsOptionalDependency_WhenBookServiceIsRetrievedFromContext_ThenNoSuchBeanDefinitionExceptionIsNotThrown() {
		BookService bookService = context.getBean(BookService.class);
		assertNotNull(bookService);
	}

	@Test
	void givenSpringPersonServiceConstructorAnnotatedByAutowired_WhenSpringPersonServiceIsRetrievedFromContext_ThenInstanceWillBeCreatedFromTheConstructor() {
		SpringPersonService personService = context.getBean(SpringPersonService.class);
		assertNotNull(personService);
	}

	@Test
	void givenPersonDaoAutowiredToSpringPersonServiceBySetterInjection_WhenSpringPersonServiceRetrievedFromContext_ThenPersonDaoInitializedByTheSetter() {
		SpringPersonService personService = context.getBean(SpringPersonService.class);
		assertNotNull(personService);
		assertNotNull(personService.getPersonDao());
	}
}