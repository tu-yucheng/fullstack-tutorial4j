package cn.tuyucheng.taketoday.di.guice;

import cn.tuyucheng.taketoday.di.spring.*;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import lombok.SneakyThrows;

public class GuiceModule extends AbstractModule {

	@SneakyThrows
	@Override
	protected void configure() {
		bind(AccountService.class).to(AccountServiceImpl.class);
		bind(PersonDao.class).to(PersonDaoImpl.class);

		bind(Person.class).toConstructor(Person.class.getConstructor());
		bind(FooProcessor.class).toProvider(FooProcessor::new);
		// bind(Person.class).toProvider(Person::new);
		bind(Foo.class).toProvider(() -> null);
	}

	@Provides
	public BookService bookServiceGenerator() {
		return new BookServiceImpl();
	}
}