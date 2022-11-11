package cn.tuyucheng.taketoday.di.guice;

import cn.tuyucheng.taketoday.di.spring.BookService;
import cn.tuyucheng.taketoday.di.spring.FooProcessor;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GuiceUnitTest {

	private final Injector injector = Guice.createInjector(new GuiceModule());

	@Test
	void givenAccountServiceAutowiredToUserService_whenGetAccountServiceInvoked_thenReturnValueIsNotNull() {
		GuiceUserService guiceUserService = injector.getInstance(GuiceUserService.class);
		assertNotNull(guiceUserService.getAccountService());
	}

	@Test
	void givenBookServiceIsProvideByGuiceModuleWhenBookServiceIsRetrievedFromGuiceThenReturnValueIsNotNull() {
		BookService bookService = injector.getInstance(BookService.class);
		assertNotNull(bookService);
	}

	@Test
	void givenTwoBindingsToPerson_whenPersonIsRetrievedFromGuice_thenShouldThrowEx() {
		Person person = injector.getInstance(Person.class);
		assertNotNull(person);
	}

	@Test
	void givenFooBindingToNull_whenRetrievedFooInstance_thenReturnValuesIsNotNull() {
		FooProcessor fooProcessor = injector.getInstance(FooProcessor.class);
		assertNotNull(fooProcessor);
	}

	@Test
	void givenGuicePersonServiceConstructorAnnotatedByInject_WhenGuicePersonServiceIsRetrievedFromModule_ThenInstanceWillBeCreatedFromTheConstructor() {
		GuicePersonService guicePersonService = injector.getInstance(GuicePersonService.class);
		assertNotNull(guicePersonService);
		assertNotNull(guicePersonService.getPersonDao());
	}
}