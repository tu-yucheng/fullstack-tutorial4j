package cn.tuyucheng.taketoday.spring.data.persistence.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersistenceConfig.class)
class FooServiceImplIntegrationTest {

	@Autowired
	private FooService service;

	@Autowired
	private DataSource dataSource;

	@Test
	final void whenInvalidEntityIsCreated_thenDataException() {
		assertThrows(DataIntegrityViolationException.class, () -> service.create(new Foo()));
	}
}